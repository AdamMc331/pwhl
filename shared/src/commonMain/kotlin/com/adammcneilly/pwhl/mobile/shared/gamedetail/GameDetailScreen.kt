package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.ui.components.SpatialModeSwitchFAB
import dev.zacsweers.metrox.viewmodel.metroViewModel

/**
 * Stateful container for [GameDetailContent] that recomposes
 * every time we get a new state from our [viewModel].
 */
@Composable
fun GameDetailScreen(
    viewModel: GameDetailViewModel = metroViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        GameDetailContent(
            state = state.value,
            eventHandler = viewModel::handleEvent,
        )

        SpatialModeSwitchFAB(
            modifier = Modifier
                .align(Alignment.BottomEnd),
        )
    }
}
