package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameStatsDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameDetailResultDisplayModel

val testGameWinnerDetailDisplayModel = TeamGameDetailResultDisplayModel(
    team = testSirensDisplayModel,
    stats = GameStatsDisplayModel(
        goals = "10",
        shots = "",
        assists = "",
    ),
)

val testGameLoserDetailDisplayModel = TeamGameDetailResultDisplayModel(
    team = testFleetDisplayModel,
    stats = GameStatsDisplayModel(
        goals = "0",
        shots = "",
        assists = "",
    ),
)
