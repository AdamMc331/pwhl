package com.adammcneilly.pwhl.mobile

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.adammcneilly.pwhl.mobile.displaymodels.testGameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailPager
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailTab
import com.android.tools.screenshot.PreviewTest

@Composable
@PreviewLightDark
@PreviewScreenSizes
@PreviewTest
private fun GameDetailPagerTabPreview(
    @PreviewParameter(GameDetailTabPreviewParameterProvider::class) tab: GameDetailTab,
) {
    val tabs = GameDetailTab.entries
    val selectedPage = tabs.indexOf(tab)

    PWHLPreviewHelper {
        GameDetailPager(
            game = testGameDetailDisplayModel,
            tabs = tabs,
            pagerState = rememberPagerState(
                initialPage = selectedPage,
            ) {
                tabs.size
            },
        )
    }
}

private class GameDetailTabPreviewParameterProvider : PreviewParameterProvider<GameDetailTab> {
    override val values: Sequence<GameDetailTab>
        get() = GameDetailTab.entries.asSequence()

    override fun getDisplayName(
        index: Int,
    ): String {
        return GameDetailTab.entries[index].name
    }
}
