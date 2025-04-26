package com.adammcneilly.pwhl.mobile.shared.domain.usecases

import com.adammcneilly.pwhl.mobile.shared.LocalTeamImageProvider
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel

class FetchTeamUseCase {
    fun invoke(
        teamId: String,
    ): Result<TeamDisplayModel> {
        val team = when (teamId) {
            "1" -> {
                boston(teamId)
            }
            "2" -> {
                minnesota(teamId)
            }
            "3" -> {
                montreal(teamId)
            }
            "4" -> {
                newYork(teamId)
            }
            "5" -> {
                ottowa(teamId)
            }
            "6" -> {
                toronto(teamId)
            }
            else -> {
                null
            }
        }

        return if (team != null) {
            Result.success(team)
        } else {
            Result.failure(Throwable("Invalid team id: $team"))
        }
    }

    private fun toronto(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "Toronto",
            name = "Toronto Sceptres",
            shortCode = "TOR",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )

    private fun ottowa(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "Ottawa",
            name = "Ottawa Charge",
            shortCode = "OTT",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )

    private fun newYork(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "New York",
            name = "New York Sirens",
            shortCode = "NY",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )

    private fun montreal(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "Montreal",
            name = "Montreal Victoire",
            shortCode = "MTL",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )

    private fun minnesota(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "Minnesota",
            name = "Minnesota Frost",
            shortCode = "MIN",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )

    private fun boston(
        teamId: String,
    ): TeamDisplayModel =
        TeamDisplayModel(
            id = teamId,
            city = "Boston",
            name = "Boston Fleet",
            shortCode = "BOS",
            image = LocalTeamImageProvider.getTeamImage(teamId),
        )
}
