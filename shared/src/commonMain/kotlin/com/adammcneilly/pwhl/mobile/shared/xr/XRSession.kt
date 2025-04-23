package com.adammcneilly.pwhl.mobile.shared.xr

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf

/**
 * Provide an instance of an [XRSession], if available. This will be
 * configured per platform as they have their own XR frameworks.
 */
@Composable
expect fun currentXRSession(): XRSession?

val LocalXRSession = compositionLocalOf<XRSession?> {
    null
}

/**
 * Defines the functionality of an Extended Reality session in the application.
 * This will be used to create a specific experience for VR and AR situations.
 */
interface XRSession {
    val isSpatialUiEnabled: Boolean
        @Composable get

    /**
     * Minimize the app on a VR headset so it can be displayed alongside other applications.
     */
    fun requestHomeSpaceMode()

    /**
     * Expand the application in a VR headset so it can be viewed in full screen.
     */
    fun requestFullSpaceMode()
}
