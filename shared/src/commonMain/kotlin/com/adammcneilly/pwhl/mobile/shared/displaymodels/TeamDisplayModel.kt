package com.adammcneilly.pwhl.mobile.shared.displaymodels

import com.adammcneilly.pwhl.mobile.shared.LocalTeamImageProvider
import com.adammcneilly.pwhl.mobile.shared.models.Team

data class TeamDisplayModel(
    val id: String,
    val city: String,
    val name: String,
    val shortCode: String,
    val image: ImageDisplayModel,
) {
    constructor(team: Team) : this(
        id = team.id,
        city = team.city,
        name = team.name,
        shortCode = team.shortCode,
        image = team.image(),
    )

    fun imageSharedElementKey(
        gameId: String,
    ): String {
        return "team_${id}_image_game_$gameId"
    }
}

private fun Team.image(): ImageDisplayModel {
    return remoteImage() ?: LocalTeamImageProvider.getTeamImage(this.id)
}

private fun Team.remoteImage(): ImageDisplayModel? {
    return this.imageUrl?.let(ImageDisplayModel::Remote)
}
