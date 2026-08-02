package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechScoreBarSiteKitDTO(
    @SerialName("Copyright")
    val copyright: HockeyTechCopyrightDTO? = null,
    @SerialName("Parameters")
    val parameters: HockeyTechParametersDTO? = null,
    @SerialName("Scorebar")
    val scoreBar: List<HockeyTechScoreBarItemDTO?>? = null,
)
