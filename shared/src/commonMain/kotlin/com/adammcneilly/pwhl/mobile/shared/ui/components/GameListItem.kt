package com.adammcneilly.pwhl.mobile.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.ui.theme.teamGradient
import com.adammcneilly.pwhl.mobile.shared.ui.util.sharedBounds
import com.adammcneilly.pwhl.mobile.shared.ui.util.sharedElement
import com.eygraber.compose.placeholder.material3.placeholder
import com.eygraber.compose.placeholder.placeholder

@Composable
fun GameListItem(
    game: GameSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    val shape = MaterialTheme.shapes.large

    CompositionLocalProvider(
        LocalContentColor provides Color.White,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
            modifier = modifier
                .background(
                    brush = teamGradient(
                        homeTeam = game.homeTeam.team,
                        awayTeam = game.awayTeam.team,
                    ),
                    shape = shape,
                )
                .placeholder(
                    visible = game.placeholder,
                    shape = shape,
                )
                .fillMaxWidth()
                .padding(PWHLTheme.dimensions.componentPadding)
                .sharedBounds(game.headerSharedElementKey),
        ) {
            TeamImageName(
                team = game.homeTeam.team,
                gameId = game.id,
            )

            GoalsText(
                goals = game.homeTeam.goals.toString(),
                modifier = Modifier
                    .sharedBounds(
                        key = game.homeTeam.team.gameSharedElementKey(
                            gameId = game.id,
                            field = TeamDisplayModel.SharedElementFields.Goals,
                        ),
                    ),
            )

            Text(
                text = game.status,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1F)
                    .sharedBounds(game.statusSharedElementKey),
            )

            GoalsText(
                goals = game.awayTeam.goals.toString(),
                modifier = Modifier
                    .sharedBounds(
                        key = game.awayTeam.team.gameSharedElementKey(
                            gameId = game.id,
                            field = TeamDisplayModel.SharedElementFields.Goals,
                        ),
                    ),
            )

            TeamImageName(
                team = game.awayTeam.team,
                gameId = game.id,
            )
        }
    }
}

@Composable
private fun GoalsText(
    goals: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = goals,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier,
    )
}

@Composable
private fun TeamImageName(
    team: TeamDisplayModel,
    gameId: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingUltraCompact),
        modifier = modifier,
    ) {
        ImageWrapper(
            image = team.image,
            contentDescription = null,
            modifier = Modifier
                .size(PWHLTheme.dimensions.imageSizeDefault)
                .sharedElement(
                    key = team.gameSharedElementKey(
                        gameId = gameId,
                        field = TeamDisplayModel.SharedElementFields.Image,
                    ),
                ),
        )

        Text(
            text = team.shortCode,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .sharedBounds(
                    key = team.gameSharedElementKey(
                        gameId = gameId,
                        field = TeamDisplayModel.SharedElementFields.ShortCode,
                    ),
                ),
        )
    }
}
