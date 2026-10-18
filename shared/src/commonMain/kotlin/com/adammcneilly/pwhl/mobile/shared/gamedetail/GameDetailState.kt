package com.adammcneilly.pwhl.mobile.shared.gamedetail

import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel

/**
 * Defines the UI state of the game detail screen.
 */
data class GameDetailState(
    val isLoading: Boolean,
    val game: GameDetailDisplayModel?,
    val playByPlayEvents: Map<String, List<PlayByPlayEventDisplayModel>>,
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
            playByPlayEvents = emptyMap(),
            selectedMvpId = null,
        )
    }
}
