package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

@Composable
fun TeamDetailGameCard(
    game: GameSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
    ) {
        Text(
            text = "Stub game: ${game.id}",
        )
    }
}
