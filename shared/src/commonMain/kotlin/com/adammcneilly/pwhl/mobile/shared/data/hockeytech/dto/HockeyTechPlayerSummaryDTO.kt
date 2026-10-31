package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.models.Player
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechPlayerSummaryDTO(
    @SerialName("birthDate")
    val birthDate: String? = null,
    @SerialName("firstName")
    val firstName: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("jerseyNumber")
    val jerseyNumber: Int? = null,
    @SerialName("lastName")
    val lastName: String? = null,
    @SerialName("playerImageURL")
    val playerImageURL: String? = null,
    @SerialName("position")
    val position: String? = null,
) {
    fun parsePlayer(): Player {
        return Player(
            id = id.toString(),
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            jerseyNumber = jerseyNumber ?: 0,
            playerImageURL = resolveImageUrl(),
            position = position.orEmpty(),
            birthDate = birthDate.orEmpty(),
        )
    }

    /**
     * The API may or may not prepend the necessary `https`
     * at the start of the image URL, so we'll do that client side
     * just in case.
     */
    private fun resolveImageUrl(): String? {
        return playerImageURL?.let { imageUrl ->
            if (imageUrl.startsWith("https://")) {
                imageUrl
            } else {
                "https://$imageUrl"
            }
        }
    }
}
