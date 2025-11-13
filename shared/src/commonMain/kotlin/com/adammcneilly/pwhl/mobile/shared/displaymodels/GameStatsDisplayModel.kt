package com.adammcneilly.pwhl.mobile.shared.displaymodels

import com.adammcneilly.pwhl.mobile.shared.models.GameTeamStats

data class GameStatsDisplayModel(
    val goals: String,
    val shots: String,
    val assists: String,
) {
    constructor(
        gameTeamStats: GameTeamStats,
        gameStarted: Boolean,
    ) : this(
        goals = if (gameStarted) {
            gameTeamStats.goals.toString()
        } else {
            "–"
        },
        shots = if (gameStarted) {
            gameTeamStats.shots.toString()
        } else {
            "–"
        },
        assists = if (gameStarted) {
            gameTeamStats.assists.toString()
        } else {
            "–"
        },
    )
}
