package com.adammcneilly.pwhl.mobile.shared.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import com.adammcneilly.pwhl.mobile.shared.displaymodels.TeamDisplayModel
import com.materialkolor.ktx.darken
import com.materialkolor.ktx.lighten

private const val TEAM_COLOR_CHANGE_RATIO = 1.5F

@Composable
fun teamGradient(
    homeTeam: TeamDisplayModel,
    awayTeam: TeamDisplayModel,
): Brush {
    val homeTeamColor = with(PWHLColors.fromTeamId(homeTeam.id)) {
        if (isSystemInDarkTheme()) {
            this.darken(TEAM_COLOR_CHANGE_RATIO)
        } else {
            this.lighten(TEAM_COLOR_CHANGE_RATIO)
        }
    }

    val awayTeamColor = with(PWHLColors.fromTeamId(awayTeam.id)) {
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
