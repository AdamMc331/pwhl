package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.SportsHockey
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Defines a tab that will be shown on the home screen in a navigation container.
 */
enum class HomeTab(
    val label: String,
    val icon: ImageVector,
    val supportsTwoPane: Boolean,
) {
    Feed(
        label = "Feed",
        icon = Icons.Default.SportsHockey,
        supportsTwoPane = false,
    ),
    News(
        label = "News",
        icon = Icons.Default.Newspaper,
        supportsTwoPane = false,
    ),
    Standings(
        label = "Standings",
        icon = Icons.Default.BarChart,
        supportsTwoPane = false,
    ),
    Profile(
        label = "Profile",
        icon = Icons.Default.AccountCircle,
        supportsTwoPane = false,
    ),
}
