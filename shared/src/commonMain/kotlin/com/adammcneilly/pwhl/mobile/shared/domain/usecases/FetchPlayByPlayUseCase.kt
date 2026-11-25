package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.di.AppScope
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayFaceOffEvent
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
class FetchPlayByPlayUseCase
    @Inject
    constructor(
        private val repository: PWHLRepository,
    ) {
        suspend fun invoke(
            gameId: String,
        ): Result<Map<String, List<PlayByPlayEventDisplayModel>>> {
            return repository.fetchPlayByPlay(gameId).map { eventList ->
                eventList
                    .filter { event ->
                        event !is PlayByPlayFaceOffEvent
                    }
                    .map(PlayByPlayEvent::toDisplayModel)
                    .groupBy { event ->
                        event.period.longName
                    }
            }
        }
    }
