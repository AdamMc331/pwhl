package com.adammcneilly.pwhl.mobile.shared.models.playbyplay

import com.adammcneilly.pwhl.mobile.shared.LocalTeamImageProvider
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.Period
import com.adammcneilly.pwhl.mobile.shared.models.Player

data class PlayByPlayHitEvent(
    val player: Player,
    val teamId: String,
    override val period: Period,
    override val time: String,
) : PlayByPlayEvent {
    override fun toDisplayModel(): PlayByPlayEventDisplayModel {
        return PlayByPlayEventDisplayModel(
            teamImage = LocalTeamImageProvider.getTeamImage(teamId),
            time = time,
            title = "HIT",
            description = player.fullNameWithNumber,
            period = period,
        )
    }
}
