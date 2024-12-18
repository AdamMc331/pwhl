package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

class FetchGameSummaryUseCase(
    private val repository: PWHLRepository,
) {
    suspend fun invoke(
        gameId: String,
    ): Result<GameSummaryDisplayModel> {
        val pbp = repository.fetchPlayByPlay(gameId)

        println("ADAMLOG - PBP: $pbp")

        return repository.fetchGameSummary(gameId).map { game ->
            GameSummaryDisplayModel(game)
        }
    }
}