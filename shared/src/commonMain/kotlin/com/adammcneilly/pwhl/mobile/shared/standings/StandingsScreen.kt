package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.ui.components.PWHLScreenScaffold
import kotlinx.serialization.Serializable
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

    PWHLScreenScaffold(
        title = "Standings",
        modifier = modifier,
    ) { scaffoldPadding ->
        StandingsContent(
            state = state.value,
            onTeamClicked = onTeamClicked,
            modifier = Modifier
                .padding(scaffoldPadding),
        )
    }
}

/**
 * Serializable route to the standings screen used by Compose navigation.
 */
@Serializable
object StandingsScreen
