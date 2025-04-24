package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    TeamDetailContent(
        state = state.value,
        modifier = modifier,
    )
}

@Serializable
data class TeamDetailScreen(
    val teamId: String,
)
