package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameSummaryResultDisplayModel

val testGameWinnerDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testSirensDisplayModel,
    goals = 10,
    isWinner = true,
)

val testGameLoserDisplayModel = TeamGameSummaryResultDisplayModel(
    team = testFleetDisplayModel,
    goals = 0,
    isWinner = false,
)
