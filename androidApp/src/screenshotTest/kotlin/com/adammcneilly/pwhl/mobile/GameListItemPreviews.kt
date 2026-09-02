package com.adammcneilly.pwhl.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.adammcneilly.pwhl.mobile.displaymodels.testGameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.GameListItem
import com.android.tools.screenshot.PreviewTest

@Composable
@PreviewLightDark
@PreviewTest
private fun GameListItemPreviewsPreview() {
    PWHLPreviewHelper {
        GameListItem(
            game = testGameSummaryDisplayModel,
        )
    }
}
