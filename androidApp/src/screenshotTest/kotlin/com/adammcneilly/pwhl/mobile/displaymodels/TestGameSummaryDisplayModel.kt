package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

val testGameSummaryDisplayModel = GameSummaryDisplayModel(
    id = "123",
    homeTeam = testGameWinnerDisplayModel,
    awayTeam = testGameLoserDisplayModel,
    status = "Final",
    dateString = "2026-01-01",
)
