package com.adammcneilly.pwhl.mobile.shared.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

/**
 * Stateful component for [FeedContent] that observes the state
 * from the supplied [viewModel].
 */
@Composable
@OptIn(KoinExperimentalAPI::class)
fun FeedScreen(
    onGameClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    FeedContent(
        state = state.value,
        onGameClicked = onGameClicked,
        modifier = modifier,
    )
}
