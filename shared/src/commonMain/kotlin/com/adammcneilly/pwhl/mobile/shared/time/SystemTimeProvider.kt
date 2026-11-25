package com.adammcneilly.pwhl.mobile.shared.time

import com.adammcneilly.pwhl.mobile.shared.di.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import kotlin.time.Clock
import kotlin.time.Instant

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class SystemTimeProvider
    @Inject
    constructor() : TimeProvider {
        override fun now(): Instant {
            return Clock.System.now()
        }
    }
