package com.adammcneilly.pwhl.mobile.shared.gamedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameDetailDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.GameSummaryDisplayModel
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchGameDetailUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchPlayByPlayUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.collections.orEmpty

/**
 * This is the state container for a game detail screen, managing data requests
 * for a specific [gameId].
 */
class GameDetailViewModel(
    private val gameId: String,
    fetchGameDetailUseCase: FetchGameDetailUseCase,
    fetchPlayByPlayUseCase: FetchPlayByPlayUseCase,
    gameSummary: GameSummaryDisplayModel? = GameSummaryCache.get(gameId),
) : ViewModel() {
    private val initialGame = gameSummary?.let(::GameDetailDisplayModel)

    private val mutableState = MutableStateFlow(
        GameDetailState(
            isLoading = true,
            game = initialGame,
            selectedMvpId = null,
        ),
    )
    val state = mutableState.asStateFlow()

    init {
        fetchGameDetail(
            fetchGameDetailUseCase = fetchGameDetailUseCase,
            fetchPlayByPlayUseCase = fetchPlayByPlayUseCase,
        )
    }

    fun handleEvent(
        event: GameDetailUiEvent,
    ) {
        when (event) {
            is GameDetailUiEvent.MvpClicked -> selectMvp(event)
        }
    }

    private fun selectMvp(
        event: GameDetailUiEvent.MvpClicked,
    ) {
        mutableState.update { currentState ->
            val newPlayerId = if (currentState.selectedMvpId == event.playerId) {
                null
            } else {
                event.playerId
            }

            currentState.copy(
                selectedMvpId = newPlayerId,
            )
        }
    }

    private fun fetchGameDetail(
        fetchGameDetailUseCase: FetchGameDetailUseCase,
        fetchPlayByPlayUseCase: FetchPlayByPlayUseCase,
    ) {
        viewModelScope.launch {
            mutableState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                )
            }

            val gameJob = async {
                fetchGameDetailUseCase
                    .invoke(gameId)
                    .getOrNull()
            }

            val pbpEventsJob = async {
                fetchPlayByPlayUseCase
                    .invoke(gameId)
                    .getOrNull()
                    .orEmpty()
            }

            val game = gameJob.await()
            val pbpEvents = pbpEventsJob.await()

            val resolvedGame = game?.copy(
                playByPlayEvents = pbpEvents,
            )

            mutableState.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    game = resolvedGame,
                )
            }
        }
    }
}
