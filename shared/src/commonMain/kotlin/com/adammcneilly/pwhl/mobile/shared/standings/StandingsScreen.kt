package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@OptIn(KoinExperimentalAPI::class)
fun StandingsScreen(
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StandingsViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    StandingsContent(
        state = state.value,
        onTeamClicked = onTeamClicked,
        modifier = modifier,
    )
}
