package com.adammcneilly.pwhl.mobile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.adammcneilly.pwhl.mobile.displaymodels.testCompletedGameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.displaymodels.testUpcomingGameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.feed.FeedContent
import com.adammcneilly.pwhl.mobile.shared.feed.FeedState
import com.android.tools.screenshot.PreviewTest

@Composable
@PreviewLightDark
@PreviewTest
private fun FeedContentLoadedPreview() {
    PWHLScreenPreviewHelper {
        FeedContent(
            state = FeedState(
                loadingRecentGames = false,
                loadingUpcomingGames = false,
                recentGames = mapOf(
                    "Recent" to listOf(
                        testCompletedGameSummaryDisplayModel,
                        testCompletedGameSummaryDisplayModel,
                        testCompletedGameSummaryDisplayModel,
                    ),
                ),
                upcomingGames = mapOf(
                    "Upcoming" to listOf(
                        testUpcomingGameSummaryDisplayModel,
                        testUpcomingGameSummaryDisplayModel,
                        testUpcomingGameSummaryDisplayModel,
                    ),
                ),
            ),
            onGameClicked = {},
            contentPadding = PaddingValues(),
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}

@Composable
@PreviewLightDark
@PreviewTest
private fun FeedContentLoadingPreview() {
    PWHLScreenPreviewHelper {
        FeedContent(
            state = FeedState(
                loadingRecentGames = true,
                loadingUpcomingGames = true,
                recentGames = emptyMap(),
                upcomingGames = emptyMap(),
            ),
            onGameClicked = {},
            contentPadding = PaddingValues(),
        )
    }
}
