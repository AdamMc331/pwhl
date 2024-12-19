package com.adammcneilly.pwhl.mobile.shared.models

data class GamePlayerStats(
    val assists: Int,
    val goals: Int,
    val points: Int,
    val shotsAgainst: Int?,
    val goalsAgainst: Int?,
    val saves: Int?,
)