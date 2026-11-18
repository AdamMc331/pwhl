package com.adammcneilly.pwhl.mobile.shared.gamedetail

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel

/**
 * A simple in-memory cache to store [GameSummaryDisplayModel] by game ID when navigating
 * from a game summary screen (like Feed or Team Detail) to the [GameDetailScreen].
 */
object GameSummaryCache {
    private val cache = mutableMapOf<String, GameSummaryDisplayModel>()

    fun put(
        game: GameSummaryDisplayModel,
    ) {
        cache[game.id] = game
    }

    fun get(
        gameId: String,
    ): GameSummaryDisplayModel? {
        return cache[gameId]
    }
}
