package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.PlayerHighlightCard
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun GameDetailSummaryTab(
    game: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
        modifier = modifier,
    ) {
        mvpSection(game)

        venueSection(game)

        attendanceSection(game)
    }
}

private fun LazyListScope.attendanceSection(
    game: GameDetailDisplayModel,
) {
    item {
        Column(
            modifier = Modifier
                .padding(horizontal = PWHLTheme.dimensions.screenPaddingHorizontal),
        ) {
            Text(
                text = "Attendance",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        vertical = PWHLTheme.dimensions.itemSpacingCompact,
                    ),
            )

            Text(
                text = game.attendance?.toString().orEmpty(),
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

private fun LazyListScope.venueSection(
    game: GameDetailDisplayModel,
) {
    item {
        Column(
            modifier = Modifier
                .padding(horizontal = PWHLTheme.dimensions.screenPaddingHorizontal),
        ) {
            Text(
                text = "Venue",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        vertical = PWHLTheme.dimensions.itemSpacingCompact,
                    ),
            )

            Text(
                text = game.venue,
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}

private fun LazyListScope.mvpSection(
    game: GameDetailDisplayModel,
) {
    if (game.mostValuablePlayers.isEmpty()) {
        return
    }

    item {
        Column {
            Text(
                text = "Most Valuable Players",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        horizontal = PWHLTheme.dimensions.screenPaddingHorizontal,
                        vertical = PWHLTheme.dimensions.itemSpacingCompact,
                    ),
            )

            LazyRow(
                contentPadding = PaddingValues(
                    horizontal = PWHLTheme.dimensions.screenPaddingHorizontal,
                ),
                horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
            ) {
                items(game.mostValuablePlayers) { mvp ->
                    PlayerHighlightCard(
                        playerHighlight = mvp,
                    )
                }
            }
        }
    }
}
