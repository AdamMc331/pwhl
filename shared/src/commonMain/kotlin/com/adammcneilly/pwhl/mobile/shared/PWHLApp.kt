package com.adammcneilly.pwhl.mobile.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.adammcneilly.pwhl.mobile.shared.appbars.PWHLBottomBar
import com.adammcneilly.pwhl.mobile.shared.di.appModules
import com.adammcneilly.pwhl.mobile.shared.ui.theme.Dimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.LocalDimensions
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.xr.LocalXRSession
import com.adammcneilly.pwhl.mobile.shared.xr.currentXRSession
import org.koin.compose.KoinApplication

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

    CompositionLocalProvider(
        LocalXRSession provides xrSession,
        LocalDimensions provides dimensions,
    ) {
        KoinApplication(
            application = {
                modules(appModules)
            },
        ) {
            PWHLTheme {
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        PWHLBottomBar(
                            navController = navController,
                        )
                    },
                ) { scaffoldPadding ->
                    AppNavHost(
                        navController = navController,
                        modifier = Modifier
                            .padding(scaffoldPadding)
                            .fillMaxSize(),
                    )
                }
            }
        }
    }
}
