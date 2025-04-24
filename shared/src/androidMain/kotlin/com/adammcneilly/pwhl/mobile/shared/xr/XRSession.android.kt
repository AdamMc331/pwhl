package com.adammcneilly.pwhl.mobile.shared.xr

import androidx.compose.runtime.Composable
import androidx.xr.compose.platform.LocalSpatialCapabilities
import androidx.xr.compose.platform.LocalSpatialConfiguration
import androidx.xr.compose.platform.SpatialConfiguration

@Composable
actual fun currentXRSession(): XRSession? {
    return LocalSpatialConfiguration.current.let(::AndroidXRSession)
}

private class AndroidXRSession(
    val spatialConfiguration: SpatialConfiguration,
) : XRSession {
    override val isSpatialUiEnabled: Boolean
        @Composable get() = LocalSpatialCapabilities.current.isSpatialUiEnabled

    override fun requestHomeSpaceMode() {
        spatialConfiguration.requestHomeSpaceMode()
    }

    override fun requestFullSpaceMode() {
        spatialConfiguration.requestFullSpaceMode()
    }
}
