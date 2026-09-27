package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechStandingsResponseDTO(
    @SerialName("SiteKit")
    val siteKit: HockeyTechStandingsSiteKitDTO? = null,
)
