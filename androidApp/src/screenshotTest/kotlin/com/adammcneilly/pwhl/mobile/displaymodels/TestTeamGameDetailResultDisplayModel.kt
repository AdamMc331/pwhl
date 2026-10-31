package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameStatsDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameDetailResultDisplayModel

val testGameWinnerDetailDisplayModel = TeamGameDetailResultDisplayModel(
    team = testSirensDisplayModel,
    stats = GameStatsDisplayModel(
        goals = "10",
        shots = "13",
        assists = "4",
    ),
)

val testGameLoserDetailDisplayModel = TeamGameDetailResultDisplayModel(
    team = testFleetDisplayModel,
    stats = GameStatsDisplayModel(
        goals = "0",
        shots = "6",
        assists = "0",
    ),
)
