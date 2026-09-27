package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlin.time.Duration.Companion.days

/**
 * Fetches a collection of games that have been played recently
 * and maps them into display models.
 */
class FetchRecentGamesUseCase(
    private val repository: PWHLRepository,
    private val timeProvider: TimeProvider,
) {
    suspend fun invoke(): Result<List<GameSummaryDisplayModel>> {
        val now = timeProvider.now()
        val afterDate = now.minus(DAYS_BACK.days)
        val request = GameListRequest(
            beforeDate = now,
            afterDate = afterDate,
            teamId = null,
        )

        return repository.fetchGames(request).map { gameList ->
            gameList.map(::GameSummaryDisplayModel)
        }
    }

    @Suppress("UndocumentedPublicClass") // Remove after Detekt is updated: https://github.com/detekt/detekt/pull/7635/
    private companion object {
        private const val DAYS_BACK = 7
    }
}
