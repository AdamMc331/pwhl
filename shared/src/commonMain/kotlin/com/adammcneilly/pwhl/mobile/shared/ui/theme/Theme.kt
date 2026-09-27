package com.adammcneilly.pwhl.mobile.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import com.materialkolor.rememberDynamicColorScheme

@Composable
fun PWHLTheme(
    seedColor: Color = PWHLColors.Purple,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = rememberDynamicColorScheme(
        seedColor = seedColor,
        isDark = useDarkTheme,
        isAmoled = false,
    )

    CompositionLocalProvider(
        LocalElevations provides Elevations.default,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            shapes = Shapes,
            content = content,
        )
    }
}

object PWHLTheme {
    val dimensions: Dimensions
        @Composable
        get() = LocalDimensions.current

    val elevations: Elevations
        @Composable
        get() = LocalElevations.current
}
