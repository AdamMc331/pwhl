package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.ui.components.PWHLScreenScaffold
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TeamDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: TeamDetailViewModel = koinViewModel(),
) {
    val state = viewModel.state.collectAsState()

    PWHLScreenScaffold(
        title = "TODO: Team Name",
        modifier = modifier,
    ) { scaffoldPadding ->
        TeamDetailContent(
            state = state.value,
            modifier = modifier
                .padding(scaffoldPadding),
        )
    }
}

@Serializable
data class TeamDetailScreen(
    val teamId: String,
)
