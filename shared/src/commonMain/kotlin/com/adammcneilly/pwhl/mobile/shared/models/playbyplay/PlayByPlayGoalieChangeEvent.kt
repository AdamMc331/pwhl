package com.adammcneilly.pwhl.mobile.shared.models.playbyplay

import com.adammcneilly.pwhl.mobile.shared.models.Period
import com.adammcneilly.pwhl.mobile.shared.models.Player

data class PlayByPlayGoalieChangeEvent(
    val goalieComingIn: Player,
    val goalieGoingOut: Player?,
    val teamId: String,
    val period: Period,
    val time: String,
)