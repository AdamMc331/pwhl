package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechParametersDTO(
    @SerialName("client_code")
    val clientCode: String? = null,
    @SerialName("division_id")
    val divisionId: String? = null,
    @SerialName("feed")
    val feed: String? = null,
    @SerialName("fmt")
    val fmt: String? = null,
    @SerialName("key")
    val key: String? = null,
    @SerialName("lang")
    val lang: String? = null,
    @SerialName("lang_id")
    val langId: Int? = null,
    @SerialName("league_code")
    val leagueCode: String? = null,
    @SerialName("league_id")
    val leagueId: Int? = null,
    @SerialName("limit")
    val limit: String? = null,
    @SerialName("numberofdaysahead")
    val numberofdaysahead: String? = null,
    @SerialName("numberofdaysback")
    val numberofdaysback: String? = null,
    @SerialName("season_id")
    val seasonId: Int? = null,
    @SerialName("site_id")
    val siteId: String? = null,
    @SerialName("view")
    val view: String? = null,
)
