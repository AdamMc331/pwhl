package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameSummaryResultDisplayModel

val testGameWinnerDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testTeamDisplayModel,
    goals = 10,
    isWinner = true,
)

val testGameLoserDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testTeamDisplayModel,
    goals = 0,
    isWinner = false,
)
