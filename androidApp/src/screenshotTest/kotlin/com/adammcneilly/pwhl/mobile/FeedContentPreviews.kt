package com.adammcneilly.pwhl.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.adammcneilly.pwhl.mobile.displaymodels.testCompletedGameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.displaymodels.testUpcomingGameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.feed.FeedContent
import com.adammcneilly.pwhl.mobile.shared.feed.FeedState
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.AppStateData
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.HomeTab
import com.android.tools.screenshot.PreviewTest

private val feedAppStateData = AppStateData(
    selectedTab = HomeTab.Feed,
)

@Composable
@PreviewLightDark
@PreviewScreenSizes
@PreviewTest
private fun FeedContentLoadedPreview() {
    PWHLPreviewHelper(
        appStateData = feedAppStateData,
    ) {
        FeedContent(
            state = FeedState(
                loadingRecentGames = false,
                loadingUpcomingGames = false,
                recentGames = mapOf(
                    "Recent" to List(3) { index ->
                        testCompletedGameSummaryDisplayModel.copy(
                            id = index.toString(),
                        )
                    },
                ),
                upcomingGames = mapOf(
                    "Upcoming" to List(3) { index ->
                        testUpcomingGameSummaryDisplayModel.copy(
                            id = index.toString(),
                        )
                    },
                ),
            ),
            onGameClicked = {},
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
