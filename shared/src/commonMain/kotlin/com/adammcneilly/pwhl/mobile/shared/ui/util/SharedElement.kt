package com.adammcneilly.pwhl.mobile.shared.ui.util

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.navigation.LocalSceneType
import com.adammcneilly.pwhl.mobile.shared.navigation.SceneType
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalSharedTransitionScope

/**
 * Custom wrapper around the Compose foundation sharedElement modifier, that will only apply
 * if we have the relevant local scopes defined. Otherwise we'll return early with the current modifier.
 */
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun Modifier.sharedElement(
    key: String,
): Modifier {
    val sharedTransitionScope = LocalSharedTransitionScope.current
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
    val localSceneType = LocalSceneType.current

    if (localSceneType == SceneType.TwoPane) {
        return this
    }

    return with(sharedTransitionScope) {
        this@sharedElement.sharedElement(
            sharedContentState = rememberSharedContentState(
                key = key,
            ),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
}
