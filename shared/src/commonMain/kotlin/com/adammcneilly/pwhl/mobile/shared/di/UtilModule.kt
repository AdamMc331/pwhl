package com.adammcneilly.pwhl.mobile.shared.di

import com.adammcneilly.pwhl.mobile.shared.time.SystemTimeProvider
import com.adammcneilly.pwhl.mobile.shared.time.TimeProvider
import org.koin.dsl.module

val utilModule = module {
    single<TimeProvider> {
        SystemTimeProvider
    }
}
