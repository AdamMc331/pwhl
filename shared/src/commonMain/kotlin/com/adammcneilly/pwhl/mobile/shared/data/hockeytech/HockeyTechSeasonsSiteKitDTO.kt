package com.adammcneilly.pwhl.mobile.shared.data.hockeytech

import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechCopyrightDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechParametersDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechScoreBarItemDTO
import com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto.HockeyTechSeasonDTO
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechSeasonsSiteKitDTO(
    @SerialName("Copyright")
    val copyright: HockeyTechCopyrightDTO? = null,
    @SerialName("Parameters")
    val parameters: HockeyTechParametersDTO? = null,
    @SerialName("Seasons")
    val seasons: List<HockeyTechSeasonDTO?>? = null,
)
