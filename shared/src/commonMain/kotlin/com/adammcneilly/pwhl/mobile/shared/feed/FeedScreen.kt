package com.adammcneilly.pwhl.mobile.shared.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dev.zacsweers.metrox.viewmodel.metroViewModel

/**
 * Stateful component for [FeedContent] that observes the state
 * from the supplied [viewModel].
 */
@Composable
fun FeedScreen(
    onGameClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = metroViewModel(),
) {
    val state = viewModel.state.collectAsState()

    FeedContent(
        state = state.value,
        onGameClicked = onGameClicked,
        modifier = modifier,
    )
}
