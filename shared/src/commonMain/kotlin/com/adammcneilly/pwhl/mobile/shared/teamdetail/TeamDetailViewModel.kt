package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchMostRecentGameForTeamUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchNextGameForTeamUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val teamId: String,
    private val fetchMostRecentGameUseCase: FetchMostRecentGameForTeamUseCase,
    private val fetchNextGameUseCase: FetchNextGameForTeamUseCase,
    private val fetchTeamUseCase: FetchTeamUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(TeamDetailState.Default)
    val state = mutableState.asStateFlow()

    init {
        requestTeam()
        requestMostRecentGame()
        requestNextGame()
    }

    private fun requestTeam() {
        val team = fetchTeamUseCase.invoke(teamId).getOrNull()

        if (team != null) {
            mutableState.update { currentState ->
                currentState.copy(
                    team = team,
                )
            }
        }
    }

    private fun requestMostRecentGame() {
        viewModelScope.launch {
            val lastGame = fetchMostRecentGameUseCase.invoke(teamId).getOrNull()

            if (lastGame != null) {
                mutableState.update { currentState ->
                    currentState.copy(
                        mostRecentGame = lastGame,
                    )
                }
            }
        }
    }

    private fun requestNextGame() {
        viewModelScope.launch {
            val nextGame = fetchNextGameUseCase.invoke(teamId).getOrNull()

            if (nextGame != null) {
                mutableState.update { currentState ->
                    currentState.copy(
                        nextGame = nextGame,
                    )
                }
            }
        }
    }
}
