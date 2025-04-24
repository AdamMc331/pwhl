package com.adammcneilly.pwhl.mobile.shared.teamdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchMostRecentGameForTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TeamDetailViewModel(
    private val teamId: String,
    private val fetchMostRecentGameUseCase: FetchMostRecentGameForTeamUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(TeamDetailState.Default)
    val state = mutableState.asStateFlow()

    init {
        requestMostRecent()
    }

    private fun requestMostRecent() {
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
}
