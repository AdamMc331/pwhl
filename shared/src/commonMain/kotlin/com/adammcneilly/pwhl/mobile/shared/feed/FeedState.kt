package com.adammcneilly.pwhl.mobile.shared.feed

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

/**
 * Defines the UI state of the feed screen.
 */
data class FeedState(
    val loadingRecentGames: Boolean,
    private val recentGames: Map<String, List<GameSummaryDisplayModel>>,
    val loadingUpcomingGames: Boolean,
    val upcomingGames: Map<String, List<GameSummaryDisplayModel>>,
) {
    val recentGamesToRender = if (loadingRecentGames) {
        mapOf(
            "" to List(3) {
                GameSummaryDisplayModel.placeholder
            },
        )
    } else {
        recentGames
    }

    companion object {
        /**
         * Provides a default implementation of the feed state
         * used when the screen is first created.
         */
        val default = FeedState(
            loadingRecentGames = false,
            recentGames = emptyMap(),
            loadingUpcomingGames = false,
            upcomingGames = emptyMap(),
        )
    }
}
