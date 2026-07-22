@file:Suppress("DpUsageRule")

package com.adammcneilly.pwhl.mobile.shared.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass

val LocalDimensions = staticCompositionLocalOf {
    Dimensions(
        componentHorizontalPadding = 0.dp,
        componentVerticalPadding = 0.dp,
        floatingButtonPadding = 0.dp,
        headerPaddingDefault = 0.dp,
        iconSizeDefault = 0.dp,
        imageSizeCompact = 0.dp,
        imageSizeDefault = 0.dp,
        imageSizeUltraCompact = 0.dp,
        itemSpacingCompact = 0.dp,
        itemSpacingDefault = 0.dp,
        itemSpacingUltraCompact = 0.dp,
        screenPaddingHorizontal = 0.dp,
        screenPaddingVertical = 0.dp,
        statLineWidth = 0.dp,
    )
}

@Immutable
data class Dimensions(
    val componentHorizontalPadding: Dp,
    val componentVerticalPadding: Dp,
    val floatingButtonPadding: Dp,
    val headerPaddingDefault: Dp,
    val iconSizeDefault: Dp,
    val imageSizeCompact: Dp,
    val imageSizeDefault: Dp,
    val imageSizeUltraCompact: Dp,
    val itemSpacingCompact: Dp,
    val itemSpacingDefault: Dp,
    val itemSpacingUltraCompact: Dp,
    val screenPaddingHorizontal: Dp,
    val screenPaddingVertical: Dp,
    val statLineWidth: Dp,
) {
    val componentPadding: PaddingValues
        get() = PaddingValues(
            horizontal = componentHorizontalPadding,
            vertical = componentVerticalPadding,
        )

    val screenPadding: PaddingValues
        get() = PaddingValues(
            horizontal = screenPaddingHorizontal,
            vertical = screenPaddingVertical,
        )

    companion object {
        private val compact = Dimensions(
            componentHorizontalPadding = 8.dp,
            componentVerticalPadding = 8.dp,
            floatingButtonPadding = 16.dp,
            headerPaddingDefault = 8.dp,
            iconSizeDefault = 16.dp,
            imageSizeCompact = 36.dp,
            imageSizeDefault = 48.dp,
            imageSizeUltraCompact = 24.dp,
            itemSpacingCompact = 8.dp,
            itemSpacingDefault = 16.dp,
            itemSpacingUltraCompact = 4.dp,
            screenPaddingHorizontal = 16.dp,
            screenPaddingVertical = 16.dp,
            statLineWidth = 6.dp,
        )

        private val medium = Dimensions(
            componentHorizontalPadding = 8.dp,
            componentVerticalPadding = 8.dp,
            floatingButtonPadding = 16.dp,
            headerPaddingDefault = 8.dp,
            iconSizeDefault = 16.dp,
            imageSizeCompact = 36.dp,
            imageSizeDefault = 48.dp,
            imageSizeUltraCompact = 24.dp,
            itemSpacingCompact = 8.dp,
            itemSpacingDefault = 16.dp,
            itemSpacingUltraCompact = 4.dp,
            screenPaddingHorizontal = 16.dp,
            screenPaddingVertical = 16.dp,
            statLineWidth = 6.dp,
        )

        private val expanded = Dimensions(
            componentHorizontalPadding = 16.dp,
            componentVerticalPadding = 16.dp,
            floatingButtonPadding = 32.dp,
            headerPaddingDefault = 8.dp,
            iconSizeDefault = 16.dp,
            imageSizeCompact = 60.dp,
            imageSizeDefault = 72.dp,
            imageSizeUltraCompact = 24.dp,
            itemSpacingCompact = 8.dp,
            itemSpacingDefault = 16.dp,
            itemSpacingUltraCompact = 4.dp,
            screenPaddingHorizontal = 16.dp,
            screenPaddingVertical = 16.dp,
            statLineWidth = 6.dp,
        )

        val immersive = Dimensions(
            componentHorizontalPadding = 16.dp,
            componentVerticalPadding = 16.dp,
            floatingButtonPadding = 32.dp,
            headerPaddingDefault = 8.dp,
            iconSizeDefault = 16.dp,
            imageSizeCompact = 84.dp,
            imageSizeDefault = 96.dp,
            imageSizeUltraCompact = 24.dp,
            itemSpacingCompact = 8.dp,
            itemSpacingDefault = 16.dp,
            itemSpacingUltraCompact = 4.dp,
            screenPaddingHorizontal = 16.dp,
            screenPaddingVertical = 16.dp,
            statLineWidth = 6.dp,
        )

        fun get(
            windowSizeClass: WindowSizeClass,
        ): Dimensions {
            return when {
                windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND) -> {
                    expanded
                }

                windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND) -> {
                    medium
                }

                else -> {
                    compact
                }
            }
        }
    }
}
