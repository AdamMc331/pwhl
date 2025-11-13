package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.gamedetail.playbyplay.PlayByPlayList

@Composable
fun GameDetailPager(
    game: GameDetailDisplayModel,
    playByPlayEvents: Map<String, List<PlayByPlayEventDisplayModel>>,
    modifier: Modifier = Modifier,
) {
    val tabs = GameDetailTab.entries

    val pagerState = rememberPagerState {
        tabs.size
    }

    Column(
        modifier = modifier,
    ) {
        GameDetailTabRow(
            pagerState = pagerState,
        )

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
        ) { pageIndex ->
            val pageModifier = Modifier
                .fillMaxSize()

            when (tabs[pageIndex]) {
                GameDetailTab.Summary -> GameDetailSummaryTab(
                    game = game,
                    modifier = pageModifier,
                )
                GameDetailTab.Stats -> GameDetailStatsTab(
                    game = game,
                    modifier = pageModifier,
                )
                GameDetailTab.PlayByPlay -> PlayByPlayList(
                    events = playByPlayEvents,
                    modifier = pageModifier,
                )
            }
        }
    }
}
