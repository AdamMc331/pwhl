package com.adammcneilly.pwhl.mobile.shared.displaymodels

import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

data class GameSummaryDisplayModel(
    val id: String,
    val homeTeam: TeamGameSummaryResultDisplayModel,
    val awayTeam: TeamGameSummaryResultDisplayModel,
    val status: String,
    val dateString: String,
) {
    constructor(game: GameSummary) : this(
        id = game.id,
        homeTeam = TeamGameSummaryResultDisplayModel(
            teamGameResult = game.homeTeam,
        ),
        awayTeam = TeamGameSummaryResultDisplayModel(
            teamGameResult = game.awayTeam,
        ),
        status = game.status,
        dateString = game.dateString(),
    )
}

private fun GameSummary.dateString(): String {
    val gameDateFormat = LocalDateTime.Format {
        dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
        char(',')
        char(' ')
        monthName(MonthNames.ENGLISH_ABBREVIATED)
        char(' ')
        day(padding = Padding.NONE)
    }

    val localDateTime = this.time.toLocalDateTime(TimeZone.currentSystemDefault())

    return localDateTime.format(gameDateFormat)
}
