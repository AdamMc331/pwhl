package com.adammcneilly.pwhl.mobile.shared.models

import kotlin.time.Instant

data class GameSummary(
    val id: String,
    val homeTeam: TeamGameSummaryResult,
    val awayTeam: TeamGameSummaryResult,
    val time: Instant,
    val status: String,
    val isComplete: Boolean,
)
