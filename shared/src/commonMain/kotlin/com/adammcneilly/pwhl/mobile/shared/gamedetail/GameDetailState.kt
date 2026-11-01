package com.adammcneilly.pwhl.mobile.shared.gamedetail

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel

/**
 * Defines the UI state of the game detail screen.
 */
data class GameDetailState(
    val isLoading: Boolean,
    val game: GameDetailDisplayModel?,
    val selectedMvpId: String?,
) {
    companion object {
        /**
         * Provides a default state for the
         * game detail screen when it is first created.
         */
        val Default = GameDetailState(
            isLoading = false,
            game = null,
            selectedMvpId = null,
        )
    }
}
