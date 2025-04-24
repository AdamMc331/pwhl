package com.adammcneilly.pwhl.mobile.shared.di

import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchGameDetailUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchMostRecentGameForTeamUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchPlayByPlayUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchRecentGamesUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchStandingsUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchTeamUseCase
import com.adammcneilly.pwhl.mobile.shared.domain.usecases.FetchUpcomingGamesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        FetchGameDetailUseCase(
            repository = get(),
        )
    }

    single {
        FetchPlayByPlayUseCase(
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

    single {
        FetchMostRecentGameForTeamUseCase(
            repository = get(),
            timeProvider = get(),
        )
    }

    single {
        FetchTeamUseCase()
    }
}
