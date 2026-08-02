package com.adammcneilly.pwhl.mobile.shared.data.hockeytech

import com.adammcneilly.pwhl.mobile.shared.data.remote.BaseKtorClient
import com.adammcneilly.pwhl.mobile.shared.fakeHttpClientEngine
import com.adammcneilly.pwhl.mobile.shared.models.Season
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import com.varabyte.truthish.assertThat
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.LocalDate
import kotlin.test.Test
import kotlin.time.Instant

class HockeyTechPWHLServiceTest {
    @Test
    fun fetchSeasons() =
        runTest {
            val responses = mapOf(
                "/feed/index.php?key=valid_api_key&client_code=pwhl&fmt=json&lang=en&league_id=1&site_id=0&feed=modulekit&view=seasons" to
                    "files/seasons_list.json",
            )

            val engine = fakeHttpClientEngine(responses)
            val apiClient = TestHockeyTechKtorClient(engine)
            val service = HockeyTechPWHLService(
                apiClient = apiClient,
                timeProvider = FakeTimeProvider(),
            )

            val result = service.fetchSeasons()

            assertThat(result.isSuccess).isTrue()
            val seasons = result.getOrThrow()
            assertThat(seasons).hasSize(2)
            assertThat(seasons[0]).isEqualTo(
                Season(
                    id = "1",
                    name = "2023-24 Season",
                    career = false,
                    playoff = false,
                    startDate = LocalDate.parse("2023-11-01"),
                    endDate = LocalDate.parse("2024-05-30"),
                ),
            )
        }

    @Test
    fun fetchStandings() =
        runTest {
            val responses = mapOf(
                "/feed/index.php?key=valid_api_key&client_code=pwhl&fmt=json&lang=en&league_id=1&site_id=0&feed=statviewfeed&view=teams&groupTeamsBy=division&context=overall&special=false&sort=points" to
                    "files/standings_list.json",
            )

            val engine = fakeHttpClientEngine(responses)
            val apiClient = TestHockeyTechKtorClient(engine)
            val service = HockeyTechPWHLService(
                apiClient = apiClient,
                timeProvider = FakeTimeProvider(),
            )

            val result = service.fetchStandings()

            assertThat(result.isSuccess).isTrue()
            val standings = result.getOrThrow()
            assertThat(standings).hasSize(6)
            assertThat(standings[0].team.name).isEqualTo("Toronto Sceptres")
        }

    @Test
    fun fetchGameDetail() =
        runTest {
            val responses = mapOf(
                "/feed/index.php?key=valid_api_key&client_code=pwhl&fmt=json&lang=en&league_id=1&site_id=0&feed=statviewfeed&view=gameSummary&game_id=113" to
                    "files/completed_game_summary.json",
            )

            val engine = fakeHttpClientEngine(responses)
            val apiClient = TestHockeyTechKtorClient(engine)
            val service = HockeyTechPWHLService(
                apiClient = apiClient,
                timeProvider = FakeTimeProvider(),
            )

            val result = service.fetchGameDetail("113")

            assertThat(result.isSuccess).isTrue()
            val detail = result.getOrThrow()
            assertThat(detail.id).isEqualTo("105")
            assertThat(detail.homeTeam.team.name).isEqualTo("Toronto Sceptres")
        }

    @Test
    fun fetchPlayByPlay() =
        runTest {
            val responses = mapOf(
                "/feed/index.php?key=valid_api_key&client_code=pwhl&fmt=json&lang=en&league_id=1&site_id=0&feed=statviewfeed&view=gameCenterPlayByPlay&game_id=113" to
                    "files/play_by_play.json",
            )

            val engine = fakeHttpClientEngine(responses)
            val apiClient = TestHockeyTechKtorClient(engine)
            val service = HockeyTechPWHLService(
                apiClient = apiClient,
                timeProvider = FakeTimeProvider(),
            )

            val result = service.fetchPlayByPlay("113")

            assertThat(result.isSuccess).isTrue()
            val events = result.getOrThrow()
            assertThat(events).isNotEmpty()
        }

    private class FakeTimeProvider(
        private val now: Instant = Instant.fromEpochSeconds(0),
    ) : TimeProvider {
        override fun now(): Instant = now
    }

    private class TestHockeyTechKtorClient(
        engine: HttpClientEngine,
    ) : BaseKtorClient(
            baseURL = "https://lscluster.hockeytech.com/",
            engine = engine,
        ) {
        override val baseParams = mapOf(
            "key" to "valid_api_key",
            "client_code" to "pwhl",
            "fmt" to "json",
            "lang" to "en",
            "league_id" to "1",
            "site_id" to "0",
        )
    }
}
