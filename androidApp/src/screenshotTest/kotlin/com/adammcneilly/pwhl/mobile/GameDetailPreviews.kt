package com.adammcneilly.pwhl.mobile

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.adammcneilly.pwhl.mobile.displaymodels.testGameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailContent
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailState
import com.android.tools.screenshot.PreviewTest

@Composable
@PreviewLightDark
@PreviewScreenSizes
@PreviewTest
private fun GameDetailLoadedPreview() {
    PWHLPreviewHelper {
        GameDetailContent(
            state = GameDetailState(
                isLoading = false,
                game = testGameDetailDisplayModel,
                selectedMvpId = null,
            ),
            eventHandler = {},
        )
    }
}

@Composable
@PreviewLightDark
@PreviewScreenSizes
@PreviewTest
private fun GameDetailLoadingPreview() {
    PWHLPreviewHelper {
        GameDetailContent(
            state = GameDetailState(
                isLoading = true,
                game = null,
                selectedMvpId = null,
            ),
            eventHandler = {},
        )
    }
}
