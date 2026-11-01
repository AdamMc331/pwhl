package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.ImageDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.Period
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent

val testGameDetailDisplayModel = GameDetailDisplayModel(
    id = "123",
    homeTeam = testGameWinnerDetailDisplayModel,
    awayTeam = testGameLoserDetailDisplayModel,
    mostValuablePlayers = List(3) {
        testPlayerHighlightDisplayModel
    },
    playByPlayEvents = mapOf(
        "1st" to listOf(
            PlayByPlayEventDisplayModel(
                teamImage = ImageDisplayModel.Placeholder,
                time = "2:27",
                title = "Title",
                description = "Description",
                period = Period(
                    id = "1",
                    shortName = "1st",
                    longName = "First",
                ),
                xLocation = 50,
                yLocation = 50,
                teamId = "123",
                type = PlayByPlayEvent.Type.SHOT,
            ),
            PlayByPlayEventDisplayModel(
                teamImage = ImageDisplayModel.Placeholder,
                time = "2:27",
                title = "Goal",
                description = "Description",
                period = Period(
                    id = "1",
                    shortName = "1st",
                    longName = "First",
                ),
                xLocation = 50,
                yLocation = 50,
                teamId = "123",
                type = PlayByPlayEvent.Type.GOAL,
            ),
        ),
    ),
    venue = "Canadian Tire Centre | Ottawa",
    attendance = 11297,
    status = "Final",
    dateString = "2026-01-01",
)
