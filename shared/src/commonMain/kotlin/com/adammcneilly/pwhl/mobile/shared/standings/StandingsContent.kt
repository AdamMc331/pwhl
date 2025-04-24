package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.ui.components.LoadingScreen

@Composable
fun StandingsContent(
    state: StandingsState,
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (state.isLoading) {
        LoadingScreen(modifier)
    } else {
        SuccessContent(
            state = state,
            onTeamClicked = onTeamClicked,
            modifier = modifier,
        )
    }
}

@Composable
private fun SuccessContent(
    state: StandingsState,
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(state.standings) { standingsRow ->
            StandingsRowListItem(
                standingsRow = standingsRow,
                modifier = Modifier
                    .clickable {
                        onTeamClicked.invoke(standingsRow.team.id)
                    },
            )

            HorizontalDivider()
        }
    }
}
