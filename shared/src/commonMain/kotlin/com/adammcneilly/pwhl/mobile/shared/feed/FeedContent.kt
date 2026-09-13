package com.adammcneilly.pwhl.mobile.shared.feed

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.navigation.LocalSceneType
import com.adammcneilly.pwhl.mobile.shared.navigation.SceneType
import com.adammcneilly.pwhl.mobile.shared.scaffold.PersistentScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationBar
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationRail
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState
import com.adammcneilly.pwhl.mobile.shared.ui.components.GameListItem
import com.adammcneilly.pwhl.mobile.shared.ui.components.LoadingScreen
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun FeedContent(
    state: FeedState,
    onGameClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
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
            Content(
                state = state,
                onGameClicked = onGameClicked,
                contentPadding = scaffoldPadding,
                modifier = modifier,
            )
        },
    )
}

@Composable
private fun Content(
    state: FeedState,
    onGameClicked: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier,
) {
    if (state.loadingRecentGames || state.loadingUpcomingGames) {
        LoadingScreen(modifier)
    } else {
        SuccessContent(
            state = state,
            onGameClicked = onGameClicked,
            contentPadding = contentPadding,
            modifier = modifier,
        )
    }
}

@Composable
private fun SuccessContent(
    state: FeedState,
    onGameClicked: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier,
) {
    LazyColumn(
        contentPadding = contentPadding.plus(PWHLTheme.dimensions.screenPadding),
        verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
        modifier = modifier,
    ) {
        gameList(
            games = state.recentGames.flatMap { it.value },
            onGameClicked = onGameClicked,
        )
    }
}

private fun LazyListScope.gameList(
    games: List<GameSummaryDisplayModel>,
    onGameClicked: (String) -> Unit,
) {
    items(games) { game ->
        GameListItem(
            game = game,
            modifier = Modifier
                .clickable {
                    onGameClicked.invoke(game.id)
                },
        )
    }
}
