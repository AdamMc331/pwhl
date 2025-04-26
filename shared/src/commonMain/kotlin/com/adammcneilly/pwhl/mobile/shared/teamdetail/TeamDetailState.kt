package com.adammcneilly.pwhl.mobile.shared.teamdetail

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel

data class TeamDetailState(
    val team: TeamDisplayModel?,
    val isLoading: Boolean,
    val mostRecentGame: GameSummaryDisplayModel?,
    val nextGame: GameSummaryDisplayModel?,
) {
    companion object {
        val Default = TeamDetailState(
            team = null,
            isLoading = true,
            mostRecentGame = null,
            nextGame = null,
        )
    }
}
