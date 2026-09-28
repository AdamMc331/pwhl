package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.data.repositories.PWHLRepository
import com.adammcneilly.pwhl.mobile.shared.data.requests.StandingsListRequest
import com.adammcneilly.pwhl.mobile.shared.displaymodels.StandingsRowDisplayModel

/**
 * Fetches the standings for the current season
 * and maps them into display models.
 */
class FetchStandingsUseCase(
    private val repository: PWHLRepository,
) {
    suspend fun invoke(
        request: StandingsListRequest,
    ): Result<List<StandingsRowDisplayModel>> {
        return repository.fetchStandings(request)
            .map { standingsList ->
                standingsList.map(::StandingsRowDisplayModel)
            }
    }
}
