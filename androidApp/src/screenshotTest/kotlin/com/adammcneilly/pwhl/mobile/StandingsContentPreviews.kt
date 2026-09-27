package com.adammcneilly.pwhl.mobile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.adammcneilly.pwhl.mobile.displaymodels.testStandingsList
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.AppStateData
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.HomeTab
import com.adammcneilly.pwhl.mobile.shared.standings.StandingsContent
import com.adammcneilly.pwhl.mobile.shared.standings.StandingsState
import com.android.tools.screenshot.PreviewTest

private val standingsAppStateData = AppStateData(
    selectedTab = HomeTab.Standings,
)

@Composable
@PreviewLightDark
@PreviewScreenSizes
@PreviewTest
private fun StandingsContentLoadedPreview() {
    PWHLPreviewHelper(
        appStateData = standingsAppStateData,
    ) {
        StandingsContent(
            state = StandingsState(
                isLoading = false,
                standings = testStandingsList,
            ),
            onTeamClicked = {},
            modifier = Modifier
                .fillMaxSize(),
        )
    }
}
