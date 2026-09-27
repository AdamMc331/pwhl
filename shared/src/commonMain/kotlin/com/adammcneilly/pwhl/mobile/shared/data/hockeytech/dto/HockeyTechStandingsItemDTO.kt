package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.adammcneilly.pwhl.mobile.shared.models.StandingsRow
import com.adammcneilly.pwhl.mobile.shared.models.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HockeyTechStandingsItemDTO(
    @SerialName("bench_minutes")
    val benchMinutes: String? = null,
    @SerialName("city")
    val city: String? = null,
    @SerialName("clinched")
    val clinched: String? = null,
    @SerialName("clinched_group_title")
    val clinchedGroupTitle: String? = null,
    @SerialName("clinched_playoff_spot")
    val clinchedPlayoffSpot: String? = null,
    @SerialName("conference_name")
    val conferenceName: String? = null,
    @SerialName("division_id")
    val divisionId: String? = null,
    @SerialName("division_name")
    val divisionName: String? = null,
    @SerialName("divisname")
    val divisname: String? = null,
    @SerialName("games_played")
    val gamesPlayed: String? = null,
    @SerialName("games_remaining")
    val gamesRemaining: String? = null,
    @SerialName("goals_against")
    val goalsAgainst: String? = null,
    @SerialName("goals_diff")
    val goalsDiff: String? = null,
    @SerialName("goals_for")
    val goalsFor: String? = null,
    @SerialName("home_record")
    val homeRecord: String? = null,
    @SerialName("losses")
    val losses: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("non_reg_losses")
    val nonRegLosses: String? = null,
    @SerialName("non_reg_wins")
    val nonRegWins: String? = null,
    @SerialName("ot_losses")
    val otLosses: String? = null,
    @SerialName("ot_wins")
    val otWins: String? = null,
    @SerialName("overall_rank")
    val overallRank: String? = null,
    @SerialName("past_10")
    val past10: String? = null,
    @SerialName("past_10_losses")
    val past10Losses: String? = null,
    @SerialName("past_10_ot_losses")
    val past10OtLosses: String? = null,
    @SerialName("past_10_ot_wins")
    val past10OtWins: String? = null,
    @SerialName("past_10_shootout_losses")
    val past10ShootoutLosses: String? = null,
    @SerialName("past_10_shootout_wins")
    val past10ShootoutWins: String? = null,
    @SerialName("past_10_ties")
    val past10Ties: String? = null,
    @SerialName("past_10_wins")
    val past10Wins: String? = null,
    @SerialName("penalty_kill_pct")
    val penaltyKillPct: String? = null,
    @SerialName("penalty_minutes")
    val penaltyMinutes: String? = null,
    @SerialName("percentage")
    val percentage: String? = null,
    @SerialName("percentage_full")
    val percentageFull: String? = null,
    @SerialName("pim_pg")
    val pimPg: String? = null,
    @SerialName("placeholder")
    val placeholder: String? = null,
    @SerialName("points")
    val points: String? = null,
    @SerialName("power_play_goals")
    val powerPlayGoals: String? = null,
    @SerialName("power_play_goals_against")
    val powerPlayGoalsAgainst: String? = null,
    @SerialName("power_play_pct")
    val powerPlayPct: String? = null,
    @SerialName("power_plays")
    val powerPlays: String? = null,
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("reg_losses")
    val regLosses: String? = null,
    @SerialName("reg_ot_losses")
    val regOtLosses: String? = null,
    @SerialName("regulation_wins")
    val regulationWins: String? = null,
    @SerialName("repeatheader")
    val repeatheader: Int? = null,
    @SerialName("row")
    val row: String? = null,
    @SerialName("shootout_attempts")
    val shootoutAttempts: String? = null,
    @SerialName("shootout_attempts_against")
    val shootoutAttemptsAgainst: String? = null,
    @SerialName("shootout_games_played")
    val shootoutGamesPlayed: String? = null,
    @SerialName("shootout_goals")
    val shootoutGoals: String? = null,
    @SerialName("shootout_goals_against")
    val shootoutGoalsAgainst: String? = null,
    @SerialName("shootout_losses")
    val shootoutLosses: String? = null,
    @SerialName("shootout_pct")
    val shootoutPct: String? = null,
    @SerialName("shootout_pct_goals_against")
    val shootoutPctGoalsAgainst: String? = null,
    @SerialName("shootout_pct_goals_for")
    val shootoutPctGoalsFor: String? = null,
    @SerialName("shootout_record")
    val shootoutRecord: String? = null,
    @SerialName("shootout_wins")
    val shootoutWins: String? = null,
    @SerialName("short_handed_goals_against")
    val shortHandedGoalsAgainst: String? = null,
    @SerialName("short_handed_goals_for")
    val shortHandedGoalsFor: String? = null,
    @SerialName("streak")
    val streak: String? = null,
    @SerialName("streak_wl")
    val streakWl: String? = null,
    @SerialName("team_code")
    val teamCode: String? = null,
    @SerialName("team_id")
    val teamId: String? = null,
    @SerialName("team_name")
    val teamName: String? = null,
    @SerialName("ties")
    val ties: String? = null,
    @SerialName("times_short_handed")
    val timesShortHanded: String? = null,
    @SerialName("visiting_record")
    val visitingRecord: String? = null,
    @SerialName("win_percentage")
    val winPercentage: String? = null,
    @SerialName("wins")
    val wins: String? = null,
) {
    fun parseStandingsRow(): StandingsRow? {
        val team = parseTeam() ?: return null

        return StandingsRow(
            rank = rank ?: 0,
            team = team,
            gamesPlayed = gamesPlayed?.toIntOrNull() ?: 0,
            gamesRemaining = gamesRemaining?.toIntOrNull() ?: 0,
            points = points?.toIntOrNull() ?: 0,
            regulationWins = regulationWins?.toIntOrNull() ?: 0,
            regulationLosses = losses?.toIntOrNull() ?: 0,
            overtimeWins = nonRegWins?.toIntOrNull() ?: 0,
            overtimeLosses = nonRegLosses?.toIntOrNull() ?: 0,
            winPercentage = percentage?.toFloatOrNull() ?: 0F,
            goalsFor = goalsFor?.toIntOrNull() ?: 0,
            goalsAgainst = goalsAgainst?.toIntOrNull() ?: 0,
        )
    }

    private fun parseTeam(): Team? {
        if (teamId == null) {
            return null
        }

        val teamCode = teamCode.orEmpty()
        val teamName = name.orEmpty()

        return Team(
            id = teamId,
            name = teamName,
            city = "",
            shortCode = teamCode,
            imageUrl = null,
        )
    }
}
