package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlin.time.Duration.Companion.days

/**
 * Fetches a list of games that will be played soon and maps
 * them into display models.
 */
class FetchUpcomingGamesUseCase(
    private val repository: PWHLRepository,
    private val timeProvider: TimeProvider,
) {
    suspend fun invoke(): Result<List<GameSummaryDisplayModel>> {
        val now = timeProvider.now()
        val beforeDate = now.plus(DAYS_AHEAD.days)
        val request = GameListRequest(
            beforeDate = beforeDate,
            afterDate = now,
            teamId = null,
        )

        return repository.fetchGames(request).map { gameList ->
            gameList.map(::GameSummaryDisplayModel)
        }
    }

    @Suppress("UndocumentedPublicClass") // Remove after Detekt is updated: https://github.com/detekt/detekt/pull/7635/
    private companion object {
        private const val DAYS_AHEAD = 3
    }
}
