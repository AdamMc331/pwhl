package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import dev.zacsweers.metrox.viewmodel.metroViewModel

@Composable
fun StandingsScreen(
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StandingsViewModel = metroViewModel(),
) {
    val state = viewModel.state.collectAsState()

    StandingsContent(
        state = state.value,
        onTeamClicked = onTeamClicked,
        modifier = modifier,
    )
}
