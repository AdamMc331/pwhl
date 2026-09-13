package com.adammcneilly.pwhl.mobile.shared.feed

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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

/**
 * Stateful component for [FeedContent] that observes the state
 * from the supplied [viewModel].
 */
@Composable
@OptIn(KoinExperimentalAPI::class)
fun FeedScreen(
    onGameClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FeedViewModel = koinViewModel(),
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
            FeedContent(
                state = state.value,
                onGameClicked = onGameClicked,
                contentPadding = scaffoldPadding,
            )
        },
    )
}
