package com.adammcneilly.pwhl.mobile.shared.data.hockeytech

import com.adammcneilly.pwhl.mobile.shared.BuildKonfig
import com.adammcneilly.pwhl.mobile.shared.data.remote.BaseKtorClient
import com.adammcneilly.pwhl.mobile.shared.data.remote.provideHttpClientEngine
import com.adammcneilly.pwhl.mobile.shared.di.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class HockeyTechKtorClient
    @Inject
    constructor() :
    BaseKtorClient(
            baseURL = "https://lscluster.hockeytech.com/",
            engine = provideHttpClientEngine(),
        ) {
        override val baseParams = mapOf(
            HockeyTechParameterKeys.API_KEY to BuildKonfig.PWHL_API_KEY,
            HockeyTechParameterKeys.CLIENT_CODE to HockeyTechParameterValues.CLIENT_CODE,
            HockeyTechParameterKeys.FORMAT to HockeyTechParameterValues.JSON_FORMAT,
            HockeyTechParameterKeys.LANGUAGE to HockeyTechParameterValues.ENGLISH,
            HockeyTechParameterKeys.LEAGUE_ID to HockeyTechParameterValues.LEAGUE_ID,
            HockeyTechParameterKeys.SITE_ID to HockeyTechParameterValues.SITE_ID,
        )
    }
