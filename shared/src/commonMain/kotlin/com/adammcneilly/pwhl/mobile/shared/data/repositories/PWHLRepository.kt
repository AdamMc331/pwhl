package com.adammcneilly.pwhl.mobile.shared.data.repositories

import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.models.GameDetail
import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.models.Season
import com.adammcneilly.pwhl.mobile.shared.models.StandingsRow
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent

/**
 * This interface defines all data requests for the PWHL application.
 * As this grows, we may consider splitting these repositories into different domains.
 *
 * Note that all functions return a domain model type. Any mapping into a display model
 * should be done inside of a use case for that feature.
 */
interface PWHLRepository {
    suspend fun fetchGames(
        request: GameListRequest,
    ): Result<List<GameSummary>>

    suspend fun fetchStandings(): Result<List<StandingsRow>>

    suspend fun fetchGameDetail(
        gameId: String,
    ): Result<GameDetail>

    suspend fun fetchPlayByPlay(
        gameId: String,
    ): Result<List<PlayByPlayEvent>>

    suspend fun fetchSeasons(): Result<List<Season>>
}
