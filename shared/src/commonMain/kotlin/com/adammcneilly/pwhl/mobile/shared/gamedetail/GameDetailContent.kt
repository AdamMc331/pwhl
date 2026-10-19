package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.gamedetail.xr.ImmersiveGameDetailContent
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.ui.theme.onTeamGradient
import com.adammcneilly.pwhl.mobile.shared.ui.theme.teamGradient
import com.adammcneilly.pwhl.mobile.shared.ui.util.sharedBounds
import com.adammcneilly.pwhl.mobile.shared.xr.LocalXRSession

@Composable
fun GameDetailContent(
    state: GameDetailState,
    eventHandler: (GameDetailUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Content(
        state = state,
        eventHandler = eventHandler,
        modifier = modifier,
    )
}

@Composable
private fun Content(
    state: GameDetailState,
    eventHandler: (GameDetailUiEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val xrSession = LocalXRSession.current

    if (xrSession?.isSpatialUiEnabled == true) {
        ImmersiveGameDetailContent(state, xrSession, eventHandler)
    } else {
        NonImmersiveContent(state, modifier)
    }
}

@Composable
private fun NonImmersiveContent(
    state: GameDetailState,
    modifier: Modifier,
) {
    val game = state.game ?: return // Placeholder logic coming soon

    Column(
        modifier = modifier,
    ) {
        CompositionLocalProvider(
            LocalContentColor provides MaterialTheme.colorScheme.onTeamGradient,
        ) {
            GameDetailHeader(
                game = game,
                modifier = Modifier
                    .background(
                        brush = teamGradient(
                            homeTeam = game.homeTeam.team,
                            awayTeam = game.awayTeam.team,
                        ),
                    )
                    .sharedBounds(game.headerSharedElementKey)
                    .fillMaxWidth()
                    .padding(PWHLTheme.dimensions.componentPadding)
                    .statusBarsPadding(),
            )
        }

        GameDetailPager(
            game = game,
            playByPlayEvents = state.playByPlayEvents,
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
