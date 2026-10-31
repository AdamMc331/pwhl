package com.adammcneilly.pwhl.mobile.shared.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.util.pipeline.PipelinePhase
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.copyTo
import io.ktor.utils.io.readByte
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.writeByte
import io.ktor.utils.io.writeFully
import io.ktor.utils.io.writer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.io.readByteArray
import kotlinx.serialization.json.Json

/**
 * Whenever we want to add params to a request, we just return a map of param
 * keys and values. The [BaseKtorClient] can map this to the request builder.
 */
private typealias RemoteParams = Map<String, Any?>

/**
 * Creates a default [httpClient] that can make requests to the supplied [baseURL].
 *
 * You can either subclass this with a specific client type,
 * like `object GitHubClient : BaseKtorClient("https://api.github.com")`,
 * or repurpose this class to represent a specific client instead.
 */
open class BaseKtorClient(
    val baseURL: String,
    val engine: HttpClientEngine,
) {
    /**
     * A collection of query parameters that should be applied to all requests by this client.
     */
    open val baseParams: RemoteParams = emptyMap()

    val httpClient = HttpClient(engine) {
        install(ContentNegotiation) {
            val converter = KotlinxSerializationConverter(
                Json {
                    ignoreUnknownKeys = true
                },
            )
            register(ContentType.Any, converter)
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    /**
     * A helper function to build the [baseURL] and [endpoint] operation and performs a get request.
     * Will also pass in the supplied [params] as necessary.
     *
     * NOTE that it is expected for endpoint to begin with a forward slash (/), it is not automatically
     * included into the full URL.
     *
     * You can call this function to get a response typed to the given generic, like so:
     * val eventResult: Result<Event> = apiClient.getResponse<Event>(endpoint = "/events/123")
     */
    @Suppress("TooGenericExceptionCaught")
    suspend inline fun <reified T : Any> getResponse(
        endpoint: String,
        params: RemoteParams = emptyMap(),
    ): Result<T> {
        val beforeTransform = PipelinePhase("BeforeTransform")
        httpClient.responsePipeline.insertPhaseBefore(HttpResponsePipeline.Transform, beforeTransform)
        httpClient.responsePipeline.intercept(beforeTransform) { (typeInfo, body) ->
            if (body is ByteReadChannel) {
                proceedWith(HttpResponseContainer(typeInfo, body.removeJsonPadding()))
            } else {
                proceedWith(HttpResponseContainer(typeInfo, body))
            }
        }

        val url = "$baseURL$endpoint"

        return try {
            val apiResult: T = httpClient
                .get(url) {
                    addParams(baseParams)
                    addParams(params)
                }.body()
            Result.success(apiResult)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

/**
 * Adds all of the [params] to this [HttpRequestBuilder] as long as they're not null.
 */
fun HttpRequestBuilder.addParams(
    params: RemoteParams,
) {
    params.forEach { (key, value) ->
        if (value != null) {
            this.parameter(key, value)
        }
    }
}

/**
 * Transforms the [ByteReadChannel] to remove any leading/trailing `([])` syntax that
 * appears in some of the PWHL api requests.
 */
@OptIn(DelicateCoroutinesApi::class)
@Suppress("MagicNumber", "LoopWithTooManyJumpStatements")
suspend fun ByteReadChannel.removeJsonPadding(): ByteReadChannel {
    val body = this

    val first = body.readByte()

    if (first != '('.code.toByte()) {
        return GlobalScope.writer {
            channel.writeByte(first)
            body.copyTo(channel)
        }.channel
    }

    return GlobalScope.writer {
        while (!body.isClosedForRead) {
            val arr = body.readRemaining(8 * 1024).readByteArray()

            if (body.isClosedForRead && arr.isNotEmpty()) {
                val first = arr[arr.size - 1]

                if (first == ')'.code.toByte()) {
                    channel.writeFully(arr, 0, arr.size - 1)
                    break
                } else {
                    channel.writeFully(arr)
                    continue
                }
            }

            channel.writeFully(arr)
        }
    }.channel
}
