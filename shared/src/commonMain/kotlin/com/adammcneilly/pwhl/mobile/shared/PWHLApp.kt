package com.adammcneilly.pwhl.mobile.shared

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.di.AppGraph
import com.adammcneilly.pwhl.mobile.shared.navigation.AppNavHost
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalSharedTransitionScope
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.AppState
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.LocalAppState
import com.adammcneilly.pwhl.mobile.shared.ui.theme.Dimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.LocalDimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.xr.LocalXRSession
import com.adammcneilly.pwhl.mobile.shared.xr.currentXRSession
import dev.zacsweers.metro.createGraph
import dev.zacsweers.metrox.viewmodel.LocalMetroViewModelFactory

@Composable
fun PWHLApp() {
    val xrSession = currentXRSession()

    // This is a temporary work around to provide a special dimension to be used in an immersive situation.
    // Ideally we don't set dimensions based on capabilities, but window size, and there will hopefully
    // be updates to Android XR in the future that make that decision easier.
    val dimensions = if (xrSession?.isSpatialUiEnabled == true) {
        Dimensions.immersive
    } else {
        Dimensions.get(currentWindowAdaptiveInfo().windowSizeClass)
    }

    val appState = rememberSaveable(saver = AppState.saver) {
        AppState()
    }

    val appGraph = remember {
        createGraph<AppGraph>()
    }

    SharedTransitionLayout(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        CompositionLocalProvider(
            LocalXRSession provides xrSession,
            LocalDimensions provides dimensions,
            LocalAppState provides appState,
            LocalSharedTransitionScope provides this,
            LocalMetroViewModelFactory provides appGraph.viewModelFactory,
        ) {
            PWHLTheme {
                AppNavHost()
            }
        }
    }
}
