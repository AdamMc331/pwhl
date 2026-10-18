package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamGameDetailResultDisplayModel
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalSharedTransitionScope
import com.adammcneilly.pwhl.mobile.shared.ui.components.ImageWrapper
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLColors
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme
import com.adammcneilly.pwhl.mobile.shared.ui.util.sharedBounds
import com.adammcneilly.pwhl.mobile.shared.ui.util.sharedElement
import com.materialkolor.ktx.darken
import com.materialkolor.ktx.lighten

private const val GAME_STATUS_WIDTH_RATIO = 0.50F
private const val TEAM_COLOR_CHANGE_RATIO = 1.5F

@Composable
fun GameDetailHeader(
    game: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(
                brush = buildHeaderGradient(game),
            )
            .sharedBounds(game.headerSharedElementKey),
    ) {
        TeamNameLogo(
            team = game.homeTeam.team,
            gameId = game.id,
        )

        TeamScore(
            teamGameResult = game.homeTeam,
            gameId = game.id,
        )

        GameSummary(game)

        TeamScore(
            teamGameResult = game.awayTeam,
            gameId = game.id,
        )

        TeamNameLogo(
            team = game.awayTeam.team,
            gameId = game.id,
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun GameSummary(
    game: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    with(LocalSharedTransitionScope.current) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingUltraCompact),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier,
        ) {
            Text(
                text = game.status,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(GAME_STATUS_WIDTH_RATIO)
                    .sharedBounds(game.statusSharedElementKey),
            )

            Text(
                text = game.dateString,
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = rememberSharedContentState(key = "game_date_${game.dateString}"),
                        animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current,
                        resizeMode = SharedTransitionScope.ResizeMode.scaleToBounds(),
                    ),
            )
        }
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun TeamScore(
    teamGameResult: TeamGameDetailResultDisplayModel,
    gameId: String,
) {
    with(LocalSharedTransitionScope.current) {
        Text(
            text = teamGameResult.stats.goals,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .sharedBounds(
                    key = teamGameResult.team.gameSharedElementKey(
                        gameId = gameId,
                        field = TeamDisplayModel.SharedElementFields.Goals,
                    ),
                ),
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
private fun TeamNameLogo(
    team: TeamDisplayModel,
    gameId: String,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingCompact),
        modifier = modifier,
    ) {
        with(LocalSharedTransitionScope.current) {
            ImageWrapper(
                image = team.image,
                contentDescription = team.name,
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
                style = MaterialTheme.typography.titleSmall,
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
}

@Composable
private fun buildHeaderGradient(
    game: GameDetailDisplayModel,
): Brush {
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

    return Brush.linearGradient(
        colors = listOf(
            homeTeamColor,
            awayTeamColor,
        ),
    )
}
