package com.adammcneilly.pwhl.mobile.shared.xr

import androidx.compose.runtime.Composable
import androidx.xr.compose.platform.LocalSession
import androidx.xr.compose.platform.LocalSpatialCapabilities
import androidx.xr.compose.platform.LocalSpatialConfiguration
import androidx.xr.compose.platform.SpatialConfiguration
import androidx.xr.runtime.Session
import androidx.xr.scenecore.scene

@Composable
actual fun currentXRSession(): XRSession? {
    return LocalSession.current?.let(::AndroidXRSession)
}

private class AndroidXRSession(
    val session: Session,
) : XRSession {
    override val isSpatialUiEnabled: Boolean
        @Composable get() = LocalSpatialCapabilities.current.isSpatialUiEnabled

    override fun requestHomeSpaceMode() {
        session.scene.requestHomeSpace()
    }

    override fun requestFullSpaceMode() {
        session.scene.requestFullSpace()
    }
}
