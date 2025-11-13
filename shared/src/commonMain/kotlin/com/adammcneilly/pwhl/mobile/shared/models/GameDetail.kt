package com.adammcneilly.pwhl.mobile.shared.models

import kotlin.time.Instant

data class GameDetail(
    val id: String,
    val homeTeam: TeamGameDetailResult,
    val awayTeam: TeamGameDetailResult,
    val mostValuablePlayers: List<PlayerStats>,
    val time: Instant,
    val status: String,
    val isStarted: Boolean,
    val isComplete: Boolean,
)
