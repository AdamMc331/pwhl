package com.adammcneilly.pwhl.mobile.shared.teamdetail

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

data class TeamDetailState(
    val teamId: String,
    val isLoading: Boolean,
    val mostRecentGame: GameSummaryDisplayModel?,
    val nextGame: GameSummaryDisplayModel?,
) {
    companion object {
        val Default = TeamDetailState(
            teamId = "",
            isLoading = true,
            mostRecentGame = null,
            nextGame = null,
        )
    }
}
