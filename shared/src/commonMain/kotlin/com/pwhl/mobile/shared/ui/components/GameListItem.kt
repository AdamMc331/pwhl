package com.pwhl.mobile.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pwhl.mobile.shared.displaymodels.GameDisplayModel
import com.pwhl.mobile.shared.displaymodels.ImageDisplayModel
import com.pwhl.mobile.shared.displaymodels.TeamDisplayModel

@Composable
fun GameListItem(
    game: GameDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(16.dp),
    ) {
        TeamRows(
            game = game,
            modifier = Modifier
                .weight(2F),
        )

        Text(
            text = game.status,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1F),
        )
    }
}

@Composable
private fun TeamRows(
    game: GameDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        TeamRow(
            team = game.homeTeam,
            goals = game.homeGoals,
        )

        TeamRow(
            team = game.awayTeam,
            goals = game.awayGoals,
        )
    }
}

@Composable
private fun TeamRow(
    team: TeamDisplayModel,
    goals: Int,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ImageWrapper(
            image = team.image ?: ImageDisplayModel.Placeholder,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp),
        )

        Spacer(
            modifier = Modifier
                .width(8.dp),
        )

        Text(
            text = team.name,
        )

        Spacer(
            modifier = Modifier
                .weight(1F),
        )

        Text(
            text = goals.toString(),
        )
    }
}
