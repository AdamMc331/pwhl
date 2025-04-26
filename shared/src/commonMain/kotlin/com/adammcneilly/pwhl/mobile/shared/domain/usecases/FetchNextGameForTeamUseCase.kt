package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlin.time.Duration.Companion.days

class FetchNextGameForTeamUseCase(
    private val repository: PWHLRepository,
    private val timeProvider: TimeProvider,
) {
    suspend operator fun invoke(
        teamId: String,
    ): Result<GameSummaryDisplayModel> {
        val request = GameListRequest(
            beforeDate = timeProvider.now().plus(10.days),
            afterDate = timeProvider.now(),
            teamId = teamId,
        )

        return repository
            .fetchGames(request)
            .mapCatching { gameList ->
                val nextGame = gameList.minByOrNull(GameSummary::time)

                requireNotNull(nextGame)

                GameSummaryDisplayModel(nextGame)
            }
    }
}
