package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.HomeTabScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState
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

    rememberScaffoldState().HomeTabScaffold(
        modifier = modifier,
        content = { scaffoldPadding ->
            StandingsContent(
                state = state.value,
                onTeamClicked = onTeamClicked,
                modifier = Modifier
                    .padding(scaffoldPadding),
            )
        },
    )
}
