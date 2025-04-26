package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLColors
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun TeamDetailContent(
    state: TeamDetailState,
    modifier: Modifier = Modifier,
) {
    PWHLTheme(
        seedColor = PWHLColors.fromTeamId(state.team?.id.orEmpty()),
    ) {
        LazyColumn(
            contentPadding = PWHLTheme.dimensions.screenPadding,
            verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
            modifier = modifier,
        ) {
            mostRecentGameSection(state)

            nextGameSection(state)
        }
    }
}

private fun LazyListScope.mostRecentGameSection(
    state: TeamDetailState,
) {
    item {
        Text(
            text = "Most Recent Game",
            style = MaterialTheme.typography.titleLarge,
        )
    }

    if (state.mostRecentGame != null) {
        item {
            TeamDetailGameCard(
                game = state.mostRecentGame,
            )
        }
    }
}

private fun LazyListScope.nextGameSection(
    state: TeamDetailState,
) {
    item {
        Text(
            text = "Next Game",
            style = MaterialTheme.typography.titleLarge,
        )
    }

    if (state.nextGame != null) {
        item {
            TeamDetailGameCard(
                game = state.nextGame,
            )
        }
    }
}
