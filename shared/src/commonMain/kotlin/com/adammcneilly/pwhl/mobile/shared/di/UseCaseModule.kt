package com.adammcneilly.pwhl.mobile.shared.di

import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchGameSummaryUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchRecentGamesUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchStandingsUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchUpcomingGamesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        FetchGameSummaryUseCase(
            repository = get(),
        )
    }

    single {
        FetchRecentGamesUseCase(
            repository = get(),
            timeProvider = get(),
        )
    }

    single {
        FetchUpcomingGamesUseCase(
            repository = get(),
            timeProvider = get(),
        )
    }

    single {
        FetchStandingsUseCase(
            repository = get(),
        )
    }
}