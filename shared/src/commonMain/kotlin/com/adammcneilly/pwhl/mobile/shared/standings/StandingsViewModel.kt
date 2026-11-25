package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.pwhl.mobile.shared.di.AppScope
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchStandingsUseCase
import dev.zacsweers.metro.ContributesIntoMap
import dev.zacsweers.metro.Inject
import dev.zacsweers.metrox.viewmodel.ViewModelKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * State container for the standings screen.
 */
@ViewModelKey
@ContributesIntoMap(AppScope::class)
class StandingsViewModel @Inject constructor(
    private val fetchStandingsUseCase: FetchStandingsUseCase,
) : ViewModel() {
    private val mutableState = MutableStateFlow(StandingsState.Default)
    val state = mutableState.asStateFlow()

    init {
        fetchStandings()
    }

    private fun fetchStandings() {
        viewModelScope.launch {
            state
                .map { state ->
                    state.buildRequest()
                }
                .distinctUntilChanged()
                .collect {
                    mutableState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                        )
                    }

                    val standings = fetchStandingsUseCase
                        .invoke(state.value.buildRequest())
                        .getOrNull()
                        .orEmpty()

                    mutableState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            standings = standings,
                        )
                    }
                }
        }
    }
}
