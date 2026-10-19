package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameSummaryResultDisplayModel

val testGameWinnerSummaryDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testSirensDisplayModel,
    goals = 10,
    isWinner = true,
)

val testGameLoserSummaryDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testFleetDisplayModel,
    goals = 0,
    isWinner = false,
)
