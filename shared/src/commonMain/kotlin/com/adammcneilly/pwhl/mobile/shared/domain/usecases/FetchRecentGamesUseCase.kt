package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.GameListRequest
import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import kotlin.time.Duration.Companion.days

class FetchRecentGamesUseCase(
    private val repository: PWHLRepository,
    private val timeProvider: TimeProvider,
) {
    suspend fun invoke(): Result<List<GameSummary>> {
        val now = timeProvider.now()
        val afterDate = now.minus(7.days)
        val request = GameListRequest(
            beforeDate = now,
            afterDate = afterDate,
            teamId = null,
        )

        return repository.fetchGames(request)
    }
}
