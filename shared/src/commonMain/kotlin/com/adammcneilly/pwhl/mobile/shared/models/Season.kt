package com.adammcneilly.pwhl.mobile.shared.models

import kotlinx.datetime.LocalDate

data class Season(
    val id: String,
    val name: String,
    val career: Boolean,
    val playoff: Boolean,
    val startDate: LocalDate,
    val endDate: LocalDate,
)
