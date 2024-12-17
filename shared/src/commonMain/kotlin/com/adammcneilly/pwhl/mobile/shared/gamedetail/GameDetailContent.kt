package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.LoadingScreen

@Composable
fun GameDetailContent(
    state: GameDetailState,
    modifier: Modifier = Modifier,
) {
    when {
        state.isLoading -> {
            LoadingScreen(
                modifier = modifier,
            )
        }
        state.game != null -> {
            SuccessContent(
                game = state.game,
                modifier = modifier,
            )
        }
        else -> {
            Text(
                text = "Something went wrong.",
                modifier = modifier,
            )
        }
    }
}

@Composable
private fun SuccessContent(
    game: GameSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        GameDetailHeader(
            game = game,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
        )
    }
}