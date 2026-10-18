package com.adammcneilly.pwhl.mobile.shared.models

data class Period(
    val id: String,
    val shortName: String,
    val longName: String,
) {
    companion object {
        val Shootout = Period(
            id = "Shootout",
            shortName = "Shootout",
            longName = "Shootout",
        )
    }
}
