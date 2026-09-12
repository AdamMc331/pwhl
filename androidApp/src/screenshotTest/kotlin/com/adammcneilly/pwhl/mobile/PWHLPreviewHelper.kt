package com.adammcneilly.pwhl.mobile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.LocalSharedTransitionScope
import com.adammcneilly.pwhl.mobile.shared.ui.components.PWHLScreenScaffold
import com.adammcneilly.pwhl.mobile.shared.ui.theme.Dimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.LocalDimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

/**
 * A helper composable that provides the [LocalSharedTransitionScope] and
 * [LocalNavAnimatedVisibilityScope] required by components that use
 * shared element transitions.
 */
@Composable
fun PWHLPreviewHelper(
    content: @Composable () -> Unit,
) {
    val dimensions = Dimensions.compact

    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            CompositionLocalProvider(
                LocalSharedTransitionScope provides this@SharedTransitionLayout,
                LocalNavAnimatedVisibilityScope provides this@AnimatedVisibility,
                LocalDimensions provides dimensions,
            ) {
                PWHLTheme {
                    content()
                }
            }
        }
    }
}

/**
 * A helper composable that provides the [LocalSharedTransitionScope] and
 * [LocalNavAnimatedVisibilityScope] required by components that use
 * shared element transitions.
 *
 * Unlike [PWHLPreviewHelper], this includes a [PWHLScreenScaffold]
 * useful for testing larger screen content previews that we want to see
 * the surface background.
 */
@Composable
fun PWHLScreenPreviewHelper(
    content: @Composable () -> Unit,
) {
    PWHLPreviewHelper {
        PWHLScreenScaffold(
            title = "Preview Screen",
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .padding(contentPadding),
            ) {
                content()
            }
        }
    }
}
