package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechScoreBarResponseDTO(
    @SerialName("SiteKit")
    val siteKit: HockeyTechScoreBarSiteKitDTO? = null,
) {
    fun parseGames(): List<GameSummary> {
        return siteKit
            ?.scoreBar
            ?.mapNotNull { scoreBarItem ->
                scoreBarItem?.parseGame()
            }.orEmpty()
    }
}
