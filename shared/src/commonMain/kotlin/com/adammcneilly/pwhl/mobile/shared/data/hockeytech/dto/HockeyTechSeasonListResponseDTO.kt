package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.HockeyTechSeasonsSiteKitDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechSeasonListResponseDTO(
    @SerialName("SiteKit")
    val siteKit: HockeyTechSeasonsSiteKitDTO? = null,
)
