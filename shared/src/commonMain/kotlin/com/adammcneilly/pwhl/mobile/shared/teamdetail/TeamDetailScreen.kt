package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamDetailViewModel = metroViewModel(),
) {
    val state = viewModel.state.collectAsState()

    TeamDetailContent(
        state = state.value,
        modifier = modifier,
    )
}
