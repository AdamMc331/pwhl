package com.adammcneilly.pwhl.mobile.shared.data.requests

import kotlin.time.Instant

/**
 * Defines information required to request a list of games.
 *
 * @property[beforeDate] All games returned should be before this date.
 * @property[afterDate] All games returned should be after this date.
 * @property[teamId] All games should have this team as either the home or away team.
 */
data class GameListRequest(
    val beforeDate: Instant,
    val afterDate: Instant,
    val teamId: String?,
)
