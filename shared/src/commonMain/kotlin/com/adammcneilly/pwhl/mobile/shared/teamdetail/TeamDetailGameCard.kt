package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.GameListItem

@Composable
fun TeamDetailGameCard(
    game: GameSummaryDisplayModel,
    onGameClicked: (GameSummaryDisplayModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    GameListItem(
        game = game,
        modifier = modifier
            .clickable(
                enabled = !game.placeholder,
            ) {
                onGameClicked.invoke(game)
            },
    )
}
