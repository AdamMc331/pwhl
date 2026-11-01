package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel

val testGameDetailDisplayModel = GameDetailDisplayModel(
    id = "123",
    homeTeam = testGameWinnerDetailDisplayModel,
    awayTeam = testGameLoserDetailDisplayModel,
    mostValuablePlayers = List(3) {
        testPlayerHighlightDisplayModel
    },
    playByPlayEvents = emptyMap(),
    status = "Final",
    dateString = "2026-01-01",
)
