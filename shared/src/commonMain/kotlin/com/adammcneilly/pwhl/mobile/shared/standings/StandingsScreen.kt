package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.navigation.LocalSceneType
import com.adammcneilly.pwhl.mobile.shared.navigation.SceneType
import com.adammcneilly.pwhl.mobile.shared.scaffold.PersistentScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationBar
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationRail
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

    rememberScaffoldState().PersistentScaffold(
        modifier = modifier,
        navigationBar = {
            PersistentNavigationBar(
                modifier = Modifier
                    .animateEnterExit(
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it }),
                    ),
            )
        },
        navigationRail = {
            if (LocalSceneType.current != SceneType.TwoPane) {
                PersistentNavigationRail()
            }
        },
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
