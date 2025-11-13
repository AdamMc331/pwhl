@file:Suppress("MagicNumber")

package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.StatComparisonDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.AnimatableStatComparison
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLColors
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun GameDetailStatsTab(
    game: GameDetailDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(PWHLTheme.dimensions.componentPadding),
    ) {
        ShotsComparison(game)

        AssistsComparison(game)

        GoalsComparison(game)
    }
}

@Composable
private fun ShotsComparison(
    gameDetail: GameDetailDisplayModel,
) {
    val homeTeamShots = gameDetail.homeTeam.stats.shots
    val awayTeamShots = gameDetail.awayTeam.stats.shots

    val homeTeamShotsInt = homeTeamShots.toIntOrNull()
    val awayTeamShotsInt = awayTeamShots.toIntOrNull()

    val homeTeamPercentage = if (homeTeamShotsInt != null && awayTeamShotsInt != null) {
        homeTeamShotsInt.toFloat() / (homeTeamShotsInt + awayTeamShotsInt)
    } else {
        0.5F
    }

    AnimatableStatComparison(
        StatComparisonDisplayModel(
            stat = "Shots",
            homeTeamValue = homeTeamShots,
            awayTeamValue = awayTeamShots,
            homeTeamColor = PWHLColors.fromTeamId(gameDetail.homeTeam.team.id),
            awayTeamColor = PWHLColors.fromTeamId(gameDetail.awayTeam.team.id),
            homeTeamPercentage = homeTeamPercentage,
        ),
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
private fun AssistsComparison(
    gameDetail: GameDetailDisplayModel,
) {
    val homeTeamAssists = gameDetail.homeTeam.stats.assists
    val awayTeamAssists = gameDetail.awayTeam.stats.assists

    val homeTeamAssistsInt = homeTeamAssists.toIntOrNull()
    val awayTeamAssistsInt = awayTeamAssists.toIntOrNull()

    val homeTeamPercentage = if (homeTeamAssistsInt != null && awayTeamAssistsInt != null) {
        homeTeamAssistsInt.toFloat() / (homeTeamAssistsInt + awayTeamAssistsInt)
    } else {
        0.5F
    }

    AnimatableStatComparison(
        StatComparisonDisplayModel(
            stat = "Assists",
            homeTeamValue = homeTeamAssists,
            awayTeamValue = awayTeamAssists,
            homeTeamColor = PWHLColors.fromTeamId(gameDetail.homeTeam.team.id),
            awayTeamColor = PWHLColors.fromTeamId(gameDetail.awayTeam.team.id),
            homeTeamPercentage = homeTeamPercentage,
        ),
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
private fun GoalsComparison(
    gameDetail: GameDetailDisplayModel,
) {
    val homeTeamGoals = gameDetail.homeTeam.stats.goals
    val awayTeamGoals = gameDetail.awayTeam.stats.goals

    val homeTeamGoalsInt = homeTeamGoals.toIntOrNull()
    val awayTeamGoalsInt = awayTeamGoals.toIntOrNull()

    val homeTeamPercentage = if (homeTeamGoalsInt != null && awayTeamGoalsInt != null) {
        homeTeamGoalsInt.toFloat() / (homeTeamGoalsInt + awayTeamGoalsInt)
    } else {
        0.5F
    }

    AnimatableStatComparison(
        StatComparisonDisplayModel(
            stat = "Goals",
            homeTeamValue = homeTeamGoals,
            awayTeamValue = awayTeamGoals,
            homeTeamColor = PWHLColors.fromTeamId(gameDetail.homeTeam.team.id),
            awayTeamColor = PWHLColors.fromTeamId(gameDetail.awayTeam.team.id),
            homeTeamPercentage = homeTeamPercentage,
        ),
        modifier = Modifier
            .fillMaxWidth(),
    )
}
