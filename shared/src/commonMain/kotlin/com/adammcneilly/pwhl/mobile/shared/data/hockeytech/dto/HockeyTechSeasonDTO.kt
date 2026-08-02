package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.models.Season
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechSeasonDTO(
    @SerialName("career")
    val career: String? = null,
    @SerialName("end_date")
    val endDate: String? = null,
    @SerialName("playoff")
    val playoff: String? = null,
    @SerialName("season_id")
    val seasonId: String? = null,
    @SerialName("season_name")
    val seasonName: String? = null,
    @SerialName("shortname")
    val shortname: String? = null,
    @SerialName("start_date")
    val startDate: String? = null,
) {
    fun parseSeason(): Season {
        return Season(
            id = this.seasonId.orEmpty(),
            name = this.seasonName.orEmpty(),
            career = (this.career == "1"),
            playoff = (this.playoff == "1"),
            startDate = LocalDate.parse(this.startDate.orEmpty()),
            endDate = LocalDate.parse(this.endDate.orEmpty()),
        )
    }
}
