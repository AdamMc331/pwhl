package com.adammcneilly.pwhl.mobile.shared.displaymodels

import com.adammcneilly.pwhl.mobile.shared.LocalTeamImageProvider
import com.adammcneilly.pwhl.mobile.shared.models.Team

data class TeamDisplayModel(
    val id: String,
    val city: String,
    val name: String,
    val shortCode: String,
    val image: ImageDisplayModel,
    val placeholder: Boolean = false,
) {
    constructor(team: Team) : this(
        id = team.id,
        city = team.city,
        name = team.name,
        shortCode = team.shortCode,
        image = team.image(),
    )

    fun gameSharedElementKey(
        gameId: String,
        field: SharedElementFields,
    ): String {
        return "team_${id}_game_${gameId}_$field"
    }

    enum class SharedElementFields {
        Image,
        ShortCode,
        Goals,
    }

    companion object {
        val placeholder = TeamDisplayModel(
            id = "123",
            city = "Placeholder",
            name = "Placeholder",
            shortCode = "PLC",
            image = ImageDisplayModel.Placeholder,
            placeholder = true,
        )
    }
}

private fun Team.image(): ImageDisplayModel {
    return remoteImage() ?: LocalTeamImageProvider.getTeamImage(this.id)
}

private fun Team.remoteImage(): ImageDisplayModel? {
    return this.imageUrl?.let(ImageDisplayModel::Remote)
}
