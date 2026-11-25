package com.adammcneilly.pwhl.mobile.shared.di

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metrox.viewmodel.ViewModelGraph

@DependencyGraph(AppScope::class)
interface AppGraph : ViewModelGraph {
    val viewModelFactory: MyViewModelFactory
}
