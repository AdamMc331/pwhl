package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameSummaryResultDisplayModel

val testCompletedGameSummaryDisplayModel = GameSummaryDisplayModel(
    id = "123",
    homeTeam = testGameWinnerDisplayModel,
    awayTeam = testGameLoserDisplayModel,
    status = "Final",
    dateString = "2026-01-01",
)

val testUpcomingGameSummaryDisplayModel = GameSummaryDisplayModel(
    id = "123",
    homeTeam = TeamGameSummaryResultDisplayModel(
        team = testFleetDisplayModel,
        goals = 0,
        isWinner = false,
    ),
    awayTeam = TeamGameSummaryResultDisplayModel(
        team = testSirensDisplayModel,
        goals = 0,
        isWinner = false,
    ),
    status = "TODO",
    dateString = "2026-01-01",
)
