package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    TeamDetailContent(
        modifier = modifier,
    )
}

@Serializable
data class TeamDetailScreen(
    val teamId: String,
)
