package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.di.AppScope
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
class FetchGameDetailUseCase @Inject constructor(
    private val repository: PWHLRepository,
) {
    suspend fun invoke(
        gameId: String,
    ): Result<GameDetailDisplayModel> {
        return repository.fetchGameDetail(gameId).map { game ->
            GameDetailDisplayModel(game)
        }
    }
}
