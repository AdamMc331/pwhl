package com.adammcneilly.pwhl.mobile.shared.displaymodels

import com.adammcneilly.pwhl.mobile.shared.models.GameDetail
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

data class GameDetailDisplayModel(
    override val id: String,
    val homeTeam: TeamGameDetailResultDisplayModel,
    val awayTeam: TeamGameDetailResultDisplayModel,
    val mostValuablePlayers: List<PlayerHighlightDisplayModel>,
    val playByPlayEvents: Map<String, List<PlayByPlayEventDisplayModel>>,
    val venue: String,
    val attendance: Int?,
    override val status: String,
    override val dateString: String,
) : BaseGameDisplayModel {
    constructor(game: GameDetail) : this(
        id = game.id,
        homeTeam = TeamGameDetailResultDisplayModel(
            teamGameResult = game.homeTeam,
            gameStarted = game.isStarted,
        ),
        awayTeam = TeamGameDetailResultDisplayModel(
            teamGameResult = game.awayTeam,
            gameStarted = game.isStarted,
        ),
        mostValuablePlayers = game.mostValuablePlayers.map(::PlayerHighlightDisplayModel),
        playByPlayEvents = emptyMap(),
        venue = game.venue,
        attendance = game.attendance,
        status = game.status,
        dateString = game.dateString(),
    )

    constructor(gameSummary: GameSummaryDisplayModel) : this(
        id = gameSummary.id,
        homeTeam = TeamGameDetailResultDisplayModel(
            team = gameSummary.homeTeam.team,
            stats = GameStatsDisplayModel(
                goals = gameSummary.homeTeam.goals.toString(),
                shots = "–",
                assists = "–",
            ),
        ),
        awayTeam = TeamGameDetailResultDisplayModel(
            team = gameSummary.awayTeam.team,
            stats = GameStatsDisplayModel(
                goals = gameSummary.awayTeam.goals.toString(),
                shots = "–",
                assists = "–",
            ),
        ),
        mostValuablePlayers = emptyList(),
        playByPlayEvents = emptyMap(),
        venue = "",
        attendance = null,
        status = gameSummary.status,
        dateString = gameSummary.dateString,
    )
}

private fun GameDetail.dateString(): String {
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
