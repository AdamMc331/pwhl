package com.adammcneilly.pwhl.mobile.shared.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLColors
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.materialkolor.ktx.darken
import com.materialkolor.ktx.lighten

private const val TEAM_COLOR_CHANGE_RATIO = 1.5F

@Composable
fun GameListItem(
    game: GameSummaryDisplayModel,
    modifier: Modifier = Modifier,
) {
    CompositionLocalProvider(
        LocalContentColor provides Color.White,
    ) {
        val homeTeamColor = with(PWHLColors.fromTeamId(game.homeTeam.team.id)) {
            if (isSystemInDarkTheme()) {
                this.darken(TEAM_COLOR_CHANGE_RATIO)
            } else {
                this.lighten(TEAM_COLOR_CHANGE_RATIO)
            }
        }

        val awayTeamColor = with(PWHLColors.fromTeamId(game.awayTeam.team.id)) {
            if (isSystemInDarkTheme()) {
                this.darken(TEAM_COLOR_CHANGE_RATIO)
            } else {
                this.lighten(TEAM_COLOR_CHANGE_RATIO)
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
            modifier = modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            homeTeamColor,
                            awayTeamColor,
                        ),
                    ),
                    shape = MaterialTheme.shapes.large,
                )
                .fillMaxWidth()
                .padding(PWHLTheme.dimensions.componentPadding),
        ) {
            TeamImageName(
                team = game.homeTeam.team,
            )

            GoalsText(
                goals = game.homeTeam.goals.toString(),
            )

            Text(
                text = game.status,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1F),
            )

            GoalsText(
                goals = game.awayTeam.goals.toString(),
            )

            TeamImageName(
                team = game.awayTeam.team,
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
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        ImageWrapper(
            image = team.image,
            contentDescription = null,
            modifier = Modifier
                .size(48.dp),
        )

        Text(
            text = team.name,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
