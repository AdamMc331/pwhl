package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.adammcneilly.pwhl.mobile.shared.scaffold.HomeTabScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState
import com.adammcneilly.pwhl.mobile.shared.ui.components.LoadingScreen
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun StandingsContent(
    state: StandingsState,
    onTeamClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().HomeTabScaffold(
        modifier = modifier,
        content = { scaffoldPadding ->
            if (state.isLoading) {
                LoadingScreen(modifier)
            } else {
                SuccessContent(
                    state = state,
                    onTeamClicked = onTeamClicked,
                    contentPadding = scaffoldPadding.plus(PWHLTheme.dimensions.screenPadding),
                    modifier = modifier
                        .nestedScroll(tabBarScrollConnection),
                )
            }
        },
    )
}

@Composable
private fun SuccessContent(
    state: StandingsState,
    onTeamClicked: (String) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
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
