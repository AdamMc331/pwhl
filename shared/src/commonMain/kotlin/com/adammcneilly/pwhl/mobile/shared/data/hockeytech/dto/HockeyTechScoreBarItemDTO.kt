package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.models.GameSummary
import com.adammcneilly.pwhl.mobile.shared.models.Team
import com.adammcneilly.pwhl.mobile.shared.models.TeamGameSummaryResult
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.time.Instant

@Serializable
data class HockeyTechScoreBarItemDTO(
    @SerialName("combined_client_code")
    val combinedClientCode: String? = null,
    @SerialName("Date")
    val date: String? = null,
    @SerialName("FloCoreEventId")
    val floCoreEventId: String? = null,
    @SerialName("FloHockeyUrl")
    val floHockeyUrl: String? = null,
    @SerialName("FloLiveEventId")
    val floLiveEventId: String? = null,
    @SerialName("GameClock")
    val gameClock: String? = null,
    @SerialName("GameDate")
    val gameDate: String? = null,
    @SerialName("GameDateISO8601")
    val gameDateISO8601: String? = null,
    @SerialName("game_letter")
    val gameLetter: String? = null,
    @SerialName("game_number")
    val gameNumber: String? = null,
    @SerialName("GameStatus")
    val gameStatus: String? = null,
    @SerialName("GameStatusString")
    val gameStatusString: String? = null,
    @SerialName("GameStatusStringLong")
    val gameStatusStringLong: String? = null,
    @SerialName("GameSummaryUrl")
    val gameSummaryUrl: String? = null,
    @SerialName("game_type")
    val gameType: String? = null,
    @SerialName("HomeAudioUrl")
    val homeAudioUrl: String? = null,
    @SerialName("HomeCity")
    val homeCity: String? = null,
    @SerialName("HomeCode")
    val homeCode: String? = null,
    @SerialName("HomeDivision")
    val homeDivision: String? = null,
    @SerialName("HomeGoals")
    val homeGoals: String? = null,
    @SerialName("HomeID")
    val homeID: String? = null,
    @SerialName("HomeLogo")
    val homeLogo: String? = null,
    @SerialName("HomeLongName")
    val homeLongName: String? = null,
    @SerialName("HomeNickname")
    val homeNickname: String? = null,
    @SerialName("HomeOTLosses")
    val homeOTLosses: String? = null,
    @SerialName("HomeRegulationLosses")
    val homeRegulationLosses: String? = null,
    @SerialName("HomeShootoutLosses")
    val homeShootoutLosses: String? = null,
    @SerialName("HomeVideoUrl")
    val homeVideoUrl: String? = null,
    @SerialName("HomeWebcastUrl")
    val homeWebcastUrl: String? = null,
    @SerialName("HomeWins")
    val homeWins: String? = null,
    @SerialName("ID")
    val iD: String? = null,
    @SerialName("Intermission")
    val intermission: String? = null,
    @SerialName("league_code")
    val leagueCode: String? = null,
    @SerialName("league_id")
    val leagueId: String? = null,
    @SerialName("league_name")
    val leagueName: String? = null,
    @SerialName("Ord")
    val ord: String? = null,
    @SerialName("Period")
    val period: String? = null,
    @SerialName("PeriodNameLong")
    val periodNameLong: String? = null,
    @SerialName("PeriodNameShort")
    val periodNameShort: String? = null,
    @SerialName("quick_score")
    val quickScore: String? = null,
    @SerialName("ScheduledFormattedTime")
    val scheduledFormattedTime: String? = null,
    @SerialName("ScheduledTime")
    val scheduledTime: String? = null,
    @SerialName("SeasonID")
    val seasonID: String? = null,
    @SerialName("TicketUrl")
    val ticketUrl: String? = null,
    @SerialName("Timezone")
    val timezone: String? = null,
    @SerialName("TimezoneShort")
    val timezoneShort: String? = null,
    @SerialName("venue_location")
    val venueLocation: String? = null,
    @SerialName("venue_name")
    val venueName: String? = null,
    @SerialName("VisitingDivision")
    val visitingDivision: String? = null,
    @SerialName("VisitorAudioUrl")
    val visitorAudioUrl: String? = null,
    @SerialName("VisitorCity")
    val visitorCity: String? = null,
    @SerialName("VisitorCode")
    val visitorCode: String? = null,
    @SerialName("VisitorGoals")
    val visitorGoals: String? = null,
    @SerialName("VisitorID")
    val visitorID: String? = null,
    @SerialName("VisitorLogo")
    val visitorLogo: String? = null,
    @SerialName("VisitorLongName")
    val visitorLongName: String? = null,
    @SerialName("VisitorNickname")
    val visitorNickname: String? = null,
    @SerialName("VisitorOTLosses")
    val visitorOTLosses: String? = null,
    @SerialName("VisitorRegulationLosses")
    val visitorRegulationLosses: String? = null,
    @SerialName("VisitorShootoutLosses")
    val visitorShootoutLosses: String? = null,
    @SerialName("VisitorVideoUrl")
    val visitorVideoUrl: String? = null,
    @SerialName("VisitorWebcastUrl")
    val visitorWebcastUrl: String? = null,
    @SerialName("VisitorWins")
    val visitorWins: String? = null,
) {
    private val isComplete = (gameStatusString == "Final")
    private val homeTeamGoalsScored = homeGoals?.toIntOrNull() ?: 0
    private val awayTeamGoalsScored = visitorGoals?.toIntOrNull() ?: 0

    private val homeTeamWins: Boolean = (isComplete && homeTeamGoalsScored > awayTeamGoalsScored)
    private val awayTeamWins: Boolean = (isComplete && awayTeamGoalsScored > homeTeamGoalsScored)

    fun parseGame(): GameSummary {
        return GameSummary(
            id = iD.orEmpty(),
            homeTeam = parseHomeTeamResult(),
            awayTeam = parseAwayTeamResult(),
            time = Instant.parse(gameDateISO8601.orEmpty()),
            status = gameStatusStringLong.orEmpty(),
            isComplete = isComplete,
        )
    }

    private fun parseHomeTeamResult(): TeamGameSummaryResult {
        return TeamGameSummaryResult(
            team = parseHomeTeam(),
            goals = homeTeamGoalsScored,
            isWinner = homeTeamWins,
        )
    }

    private fun parseAwayTeamResult(): TeamGameSummaryResult {
        return TeamGameSummaryResult(
            team = parseAwayTeam(),
            goals = awayTeamGoalsScored,
            isWinner = awayTeamWins,
        )
    }

    private fun parseHomeTeam(): Team {
        return Team(
            id = homeID.orEmpty(),
            name = homeLongName.orEmpty(),
            city = homeCity.orEmpty(),
            shortCode = homeCode.orEmpty(),
            imageUrl = homeLogo,
        )
    }

    private fun parseAwayTeam(): Team {
        return Team(
            id = visitorID.orEmpty(),
            name = visitorLongName.orEmpty(),
            city = visitorCity.orEmpty(),
            shortCode = visitorCode.orEmpty(),
            imageUrl = visitorLogo,
        )
    }
}
