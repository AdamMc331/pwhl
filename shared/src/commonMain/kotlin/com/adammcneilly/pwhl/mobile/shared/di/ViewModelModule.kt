package com.adammcneilly.pwhl.mobile.shared.di

import com.adammcneilly.pwhl.mobile.shared.feed.FeedViewModel
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailViewModel
import com.adammcneilly.pwhl.mobile.shared.standings.StandingsViewModel
import com.adammcneilly.pwhl.mobile.shared.teamdetail.TeamDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FeedViewModel(
            recentGamesUseCase = get(),
            upcomingGamesUseCase = get(),
        )
    }

    viewModel { parameters ->
        GameDetailViewModel(
            gameId = parameters.get(),
            fetchGameDetailUseCase = get(),
            fetchPlayByPlayUseCase = get(),
        )
    }

    viewModel {
        StandingsViewModel(
            fetchStandingsUseCase = get(),
        )
    }

    viewModel { parameters ->
        TeamDetailViewModel(
            teamId = parameters.get(),
            fetchMostRecentGameUseCase = get(),
            fetchTeamUseCase = get(),
        )
    }
}
