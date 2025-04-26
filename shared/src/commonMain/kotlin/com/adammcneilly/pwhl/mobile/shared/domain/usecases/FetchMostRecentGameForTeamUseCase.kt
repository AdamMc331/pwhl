package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlin.time.Duration.Companion.days

class FetchMostRecentGameForTeamUseCase(
    private val repository: PWHLRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(
        teamId: String,
    ): Result<GameSummaryDisplayModel> {
        val request = GameListRequest(
            beforeDate = timeProvider.now(),
            afterDate = timeProvider.now().minus(10.days),
            teamId = teamId,
        )

        return repository
            .fetchGames(request)
            .mapCatching { gameList ->
                val mostRecentGame = gameList.maxByOrNull(GameSummary::time)

                requireNotNull(mostRecentGame)

                GameSummaryDisplayModel(mostRecentGame)
            }
    }
}
