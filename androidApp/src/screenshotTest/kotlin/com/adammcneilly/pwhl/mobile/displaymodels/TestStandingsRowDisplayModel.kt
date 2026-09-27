package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.StandingsRowDisplayModel

private const val TEST_TEAM_COUNT = 12

val testStandingsRowDisplayModel = StandingsRowDisplayModel(
    rank = 1,
    team = testSirensDisplayModel,
    gamesPlayed = 4,
    gamesRemaining = 0,
    points = 12,
    regulationWins = 4,
    regulationLosses = 0,
    overtimeWins = 0,
    overtimeLosses = 0,
    winPercentage = 1F,
    goalsFor = 12,
    goalsAgainst = 0,
)

val testStandingsList = List(TEST_TEAM_COUNT) { index ->
    val wins = TEST_TEAM_COUNT - index
    testStandingsRowDisplayModel.copy(
        rank = index + 1,
        regulationWins = TEST_TEAM_COUNT - index,
        regulationLosses = index,
        points = (wins * 3),
    )
}
