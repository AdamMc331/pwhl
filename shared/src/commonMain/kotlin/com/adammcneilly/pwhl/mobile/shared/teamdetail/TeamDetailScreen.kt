package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamDetailScreen(
    onGameClicked: (GameSummaryDisplayModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    TeamDetailContent(
        state = state.value,
        onGameClicked = onGameClicked,
        modifier = modifier,
    )
}
