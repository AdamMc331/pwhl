@file:Suppress("DpUsageRule")

package com.adammcneilly.pwhl.mobile.shared.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LocalElevations = staticCompositionLocalOf {
    Elevations(
        level0 = 0.dp,
        level1 = 0.dp,
        level2 = 0.dp,
        level3 = 0.dp,
        level4 = 0.dp,
        level5 = 0.dp,
    )
}

@Immutable
data class Elevations(
    val level0: Dp,
    val level1: Dp,
    val level2: Dp,
    val level3: Dp,
    val level4: Dp,
    val level5: Dp,
) {
    companion object {
        val default = Elevations(
            level0 = 0.dp,
            level1 = 1.dp,
            level2 = 3.dp,
            level3 = 6.dp,
            level4 = 8.dp,
            level5 = 12.dp,
        )
    }
}
