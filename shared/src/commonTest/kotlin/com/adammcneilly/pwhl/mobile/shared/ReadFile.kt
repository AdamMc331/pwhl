package com.adammcneilly.pwhl.mobile.shared

import kotlinx.io.bytestring.ByteString
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * Reads the data from a file with the given [fileName].
 */
@OptIn(ExperimentalResourceApi::class)
suspend fun readFile(
    fileName: String,
): String {
    val readBytes = Res.readBytes(fileName)
    return ByteString(readBytes).toString()
}
