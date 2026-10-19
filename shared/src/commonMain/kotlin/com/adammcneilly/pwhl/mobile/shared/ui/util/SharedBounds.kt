package com.adammcneilly.pwhl.mobile.shared.ui.util

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.adammcneilly.pwhl.mobile.shared.navigation.LocalSceneType
import com.adammcneilly.pwhl.mobile.shared.navigation.SceneType
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalSharedTransitionScope

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun Modifier.sharedBounds(
    key: String,
    clipShapeInOverlayDuringTransition: Shape? = null,
): Modifier {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
    val sceneType = LocalSceneType.current

    if (sceneType == SceneType.TwoPane) {
        return this
    }

    val overlayClip = if (clipShapeInOverlayDuringTransition != null) {
        sharedTransitionScope.OverlayClip(clipShapeInOverlayDuringTransition)
    } else {
        ParentClip
    }

    return with(sharedTransitionScope) {
        sharedBounds(
            sharedContentState = rememberSharedContentState(
                key = key,
            ),
            animatedVisibilityScope = animatedVisibilityScope,
            clipInOverlayDuringTransition = overlayClip,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
private val ParentClip: SharedTransitionScope.OverlayClip =
    object : SharedTransitionScope.OverlayClip {
        override fun getClipPath(
            sharedContentState: SharedTransitionScope.SharedContentState,
            bounds: Rect,
            layoutDirection: LayoutDirection,
            density: Density,
        ): Path? {
            return sharedContentState.parentSharedContentState?.clipPathInOverlay
        }
    }
