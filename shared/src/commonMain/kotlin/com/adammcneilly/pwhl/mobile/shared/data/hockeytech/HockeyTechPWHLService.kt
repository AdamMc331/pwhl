package com.adammcneilly.pwhl.mobile.shared.data.hockeytech

import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechGameDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechPlayByPlayEventDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechScoreBarResponseDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechStandingsListResponseDTO
import com.adammcneilly.pwhl.mobile.shared.data.remote.BaseKtorClient
import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.models.GameDetail
import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.models.StandingsRow
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil

/**
 * This implementation of [PWHLRepository] makes all requests to the supplied
 * hockey tech [apiClient].
 */
class HockeyTechPWHLService(
    private val apiClient: BaseKtorClient,
    private val timeProvider: TimeProvider,
) : PWHLRepository {
    override suspend fun fetchGames(
        request: GameListRequest,
    ): Result<List<GameSummary>> {
        val endpoint = "feed/index.php"

        val now = timeProvider.now()
        val daysBack = request.afterDate.daysUntil(now, TimeZone.currentSystemDefault())
        val daysAhead = now.daysUntil(request.beforeDate, TimeZone.currentSystemDefault())

        val gameListParams = mapOf(
            HockeyTechParameterKeys.FEED to "modulekit",
            HockeyTechParameterKeys.VIEW to "scorebar",
            HockeyTechParameterKeys.DAYS_AHEAD to daysAhead.toString(),
            HockeyTechParameterKeys.DAYS_BACK to daysBack.toString(),
            HockeyTechParameterKeys.TEAM_ID to request.teamId.orEmpty(),
        )

        return apiClient
            .getResponse<HockeyTechScoreBarResponseDTO>(
                endpoint = endpoint,
                params = gameListParams,
            )
            .map(HockeyTechScoreBarResponseDTO::parseGames)
    }

    override suspend fun fetchStandings(): Result<List<StandingsRow>> {
        val endpoint = "feed/index.php"

        val standingsParams = mapOf(
            HockeyTechParameterKeys.FEED to "statviewfeed",
            HockeyTechParameterKeys.VIEW to "teams",
            HockeyTechParameterKeys.GROUP_TEAMS_BY to "division",
            HockeyTechParameterKeys.CONTEXT to "overall",
            HockeyTechParameterKeys.SEASON to "5", // Is this needed?
            HockeyTechParameterKeys.SPECIAL to "false", // What does this mean?
            HockeyTechParameterKeys.SORT to "points",
        )

        return apiClient.getResponse<HockeyTechStandingsListResponseDTO>(
            endpoint = endpoint,
            params = standingsParams,
        ).map { standingsList ->
            standingsList
                .firstOrNull()
                ?.sections
                ?.firstOrNull()
                ?.data
                ?.mapNotNull { data ->
                    data?.parseStandingsRow()
                }
                .orEmpty()
        }
    }

    override suspend fun fetchGameDetail(
        gameId: String,
    ): Result<GameDetail> {
        val endpoint = "feed/index.php"

        val gameParams = mapOf(
            HockeyTechParameterKeys.FEED to "statviewfeed",
            HockeyTechParameterKeys.VIEW to "gameSummary",
            HockeyTechParameterKeys.GAME_ID to gameId,
        )

        return apiClient.getResponse<HockeyTechGameDTO>(
            endpoint = endpoint,
            params = gameParams,
        ).map(HockeyTechGameDTO::parseGameDetail)
    }

    override suspend fun fetchPlayByPlay(
        gameId: String,
    ): Result<List<PlayByPlayEvent>> {
        val endpoint = "feed/index.php"

        val gameParams = mapOf(
            HockeyTechParameterKeys.FEED to "statviewfeed",
            HockeyTechParameterKeys.VIEW to "gameCenterPlayByPlay",
            HockeyTechParameterKeys.GAME_ID to gameId,
        )

        return apiClient.getResponse<List<HockeyTechPlayByPlayEventDTO>>(
            endpoint = endpoint,
            params = gameParams,
        ).map { eventList ->
            eventList.map(HockeyTechPlayByPlayEventDTO::parsePlayByPlayEvent)
        }
    }
}
