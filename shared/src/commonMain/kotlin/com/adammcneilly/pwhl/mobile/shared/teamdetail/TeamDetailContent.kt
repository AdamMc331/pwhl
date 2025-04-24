package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TeamDetailContent(
    state: TeamDetailState,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Team Detail Stub - most recent game: ${state.mostRecentGame}",
        modifier = modifier,
    )
}
