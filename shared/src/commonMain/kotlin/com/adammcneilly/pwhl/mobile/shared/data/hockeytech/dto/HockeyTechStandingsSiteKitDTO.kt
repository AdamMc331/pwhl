package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechStandingsSiteKitDTO(
    @SerialName("Copyright")
    val copyright: HockeyTechCopyrightDTO? = null,
    @SerialName("Parameters")
    val parameters: HockeyTechParametersDTO? = null,
    @SerialName("Statviewtype")
    val standings: List<HockeyTechStandingsItemDTO>? = null,
)
