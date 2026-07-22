package com.adammcneilly.pwhl.mobile.shared.feed

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.LocalSharedTransitionScope
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.GameListItem
import com.adammcneilly.pwhl.mobile.shared.ui.components.LoadingScreen
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun FeedContent(
    state: FeedState,
    onGameClicked: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
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
        contentPadding = contentPadding,
        modifier = modifier,
    ) {
        upcomingGamesHeader()

        gamesByDateGroup(state.upcomingGames, onGameClicked)

        recentGamesHeader()

        gamesByDateGroup(state.recentGames, onGameClicked)
    }
}

private fun LazyListScope.recentGamesHeader() {
    item {
        LargeHeader(
            text = "Recent Games",
        )
    }
}

private fun LazyListScope.upcomingGamesHeader() {
    item {
        LargeHeader(
            text = "Upcoming Games",
        )
    }
}

private fun LazyListScope.gamesByDateGroup(
    gamesByDate: Map<String, List<GameSummaryDisplayModel>>,
    onGameClicked: (String) -> Unit,
) {
    gamesByDate.entries.forEach { (dateString, games) ->
        item {
            GameDateHeader(dateString)
        }

        gameList(games, onGameClicked)
    }
}

private fun LazyListScope.gameList(
    games: List<GameSummaryDisplayModel>,
    onGameClicked: (String) -> Unit,
) {
    itemsIndexed(games) { index, game ->
        GameListItem(
            game = game,
            modifier = Modifier
                .clickable {
                    onGameClicked.invoke(game.id)
                },
        )

        if (index != games.lastIndex) {
            HorizontalDivider()
        }
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun GameDateHeader(
    text: String,
) {
    with(LocalSharedTransitionScope.current) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier
                .padding(PWHLTheme.dimensions.headerPaddingDefault)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "game_date_$text"),
                    animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current,
                    resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(),
                ),
        )
    }
}

@Composable
private fun LargeHeader(
    text: String,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(PWHLTheme.dimensions.headerPaddingDefault),
    )
}
