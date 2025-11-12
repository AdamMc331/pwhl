package com.adammcneilly.pwhl.mobile.shared.gamedetail.xr

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloseFullscreen
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.xr.compose.spatial.ContentEdge
import androidx.xr.compose.spatial.Orbiter
import androidx.xr.compose.spatial.OrbiterOffsetType
import androidx.xr.compose.spatial.SpatialElevation
import androidx.xr.compose.spatial.SpatialElevationLevel
import androidx.xr.compose.spatial.Subspace
import androidx.xr.compose.subspace.SpatialColumn
import androidx.xr.compose.subspace.SpatialPanel
import androidx.xr.compose.subspace.SpatialRow
import androidx.xr.compose.subspace.layout.SpatialRoundedCornerShape
import androidx.xr.compose.subspace.layout.SubspaceModifier
import androidx.xr.compose.subspace.layout.fillMaxWidth
import androidx.xr.compose.subspace.layout.height
import androidx.xr.compose.subspace.layout.padding
import androidx.xr.compose.subspace.layout.width
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailState
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailStatsTab
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailUiEvent
import com.adammcneilly.pwhl.mobile.shared.gamedetail.playbyplay.PlayByPlayList
import com.adammcneilly.pwhl.mobile.shared.ui.components.PlayerHighlightCard
import com.adammcneilly.pwhl.mobile.shared.ui.components.SpatialSurface
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.xr.XRSession

@Suppress("DpUsageRule")
private val IMMERSIVE_GAME_DETAIL_WIDTH = 1400.dp

@Suppress("DpUsageRule")
private val IMMERSIVE_DETAIL_PANEL_HEIGHT = 1000.dp

@Composable
actual fun ImmersiveGameDetailContent(
    state: GameDetailState,
    xrSession: XRSession,
    eventHandler: (GameDetailUiEvent) -> Unit,
) {
    if (state.game == null) {
        // Need to handle loading/error state.
        // Ideally we don't even allow entering immersive UI without game.
        return
    }

    Subspace {
        SpatialRow(
            modifier = SubspaceModifier
                .width(IMMERSIVE_GAME_DETAIL_WIDTH),
        ) {
            StatsPanel(
                modifier = SubspaceModifier
                    .height(IMMERSIVE_DETAIL_PANEL_HEIGHT)
                    .weight(1F),
            )

            SpatialColumn(
                modifier = SubspaceModifier
                    .height(IMMERSIVE_DETAIL_PANEL_HEIGHT)
                    .weight(1F)
                    .padding(
                        horizontal = PWHLTheme.dimensions.itemSpacingDefault,
                    ),
            ) {
                Header(
                    xrSession = xrSession,
                    game = state.game,
                )

                MVPPanel(
                    game = state.game,
                    selectedMvpId = state.selectedMvpId,
                    onMvpClicked = { mvpId ->
                        val event = GameDetailUiEvent.MvpClicked(mvpId)
                        eventHandler.invoke(event)
                    },
                    modifier = SubspaceModifier
                        .weight(1F),
                )
            }

            PlayByPlayPanel(
                state = state,
                modifier = SubspaceModifier
                    .height(IMMERSIVE_DETAIL_PANEL_HEIGHT)
                    .weight(1F),
            )
        }
    }
}

@Composable
private fun Header(
    xrSession: XRSession,
    game: GameDetailDisplayModel,
    modifier: SubspaceModifier = SubspaceModifier,
) {
    SpatialSurface(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = PWHLTheme.dimensions.itemSpacingDefault),
    ) {
        EnterHomeSpaceButton(xrSession)

        ImmersiveGameDetailHeader(
            game = game,
        )
    }
}

@Composable
private fun EnterHomeSpaceButton(
    xrSession: XRSession,
) {
    Orbiter(
        position = ContentEdge.Top,
        offset = PWHLTheme.dimensions.itemSpacingCompact,
        offsetType = OrbiterOffsetType.InnerEdge,
        alignment = Alignment.End,
    ) {
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .padding(end = PWHLTheme.dimensions.screenPaddingHorizontal),
        ) {
            IconButton(
                onClick = {
                    xrSession.requestHomeSpaceMode()
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier
                    .size(PWHLTheme.dimensions.iconSizeDefault),
            ) {
                Icon(
                    imageVector = Icons.Default.CloseFullscreen,
                    contentDescription = "Exit Full Space Mode",
                )
            }
        }
    }
}

@Composable
private fun MVPPanel(
    game: GameDetailDisplayModel,
    selectedMvpId: String?,
    onMvpClicked: (String) -> Unit,
    modifier: SubspaceModifier = SubspaceModifier,
) {
    SpatialPanel(
        shape = SpatialRoundedCornerShape(ZeroCornerSize),
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingCompact),
        ) {
            game.mostValuablePlayers.forEach { mvp ->
                val spatialElevationLevel = if (mvp.player.id == selectedMvpId) {
                    SpatialElevationLevel.Level5
                } else {
                    SpatialElevationLevel.Level0
                }

                SpatialElevation(
                    elevation = spatialElevationLevel,
                ) {
                    PlayerHighlightCard(
                        playerHighlight = mvp,
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .clickable {
                                onMvpClicked.invoke(mvp.player.id)
                            }
                            .fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun StatsPanel(
    modifier: SubspaceModifier = SubspaceModifier,
) {
    SpatialSurface(
        modifier = modifier,
    ) {
        GameDetailStatsTab()
    }
}

@Composable
private fun PlayByPlayPanel(
    state: GameDetailState,
    modifier: SubspaceModifier = SubspaceModifier,
) {
    SpatialSurface(
        modifier = modifier,
    ) {
        PlayByPlayList(
            events = state.playByPlayEvents,
        )
    }
}
