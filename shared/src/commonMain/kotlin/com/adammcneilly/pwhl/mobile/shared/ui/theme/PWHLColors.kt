@file:Suppress("MagicNumber")

package com.adammcneilly.pwhl.mobile.shared.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Defines hex code colors for PWHL teams and other branding situations.
 */
object PWHLColors {
    val Purple = Color(0xFF200255)
    val Boston = Color(0xFF173F34)
    val Minnesota = Color(0xFF250E62)
    val Montreal = Color(0xFF852533)
    val NewYork = Color(0xFF041E42)
    val Ottawa = Color(0xFFA5192E)
    val Seattle = Color(0XFF8CB7C9)
    val Toronto = Color(0xFF0166B9)
    val Vancouver = Color(0xFF10447F)

    fun fromTeamId(
        id: String,
    ): Color {
        return when (id) {
            "1" -> Boston
            "2" -> Minnesota
            "3" -> Montreal
            "4" -> NewYork
            "5" -> Ottawa
            "6" -> Toronto
            "8" -> Seattle
            "9" -> Vancouver
            else -> Purple
        }
    }
}
