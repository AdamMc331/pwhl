package com.adammcneilly.pwhl.mobile.shared.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import androidx.window.core.layout.WindowSizeClass
import com.adammcneilly.pwhl.mobile.shared.feed.FeedScreen
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailScreen
import com.adammcneilly.pwhl.mobile.shared.news.NewsScreen
import com.adammcneilly.pwhl.mobile.shared.profile.ProfileScreen
import com.adammcneilly.pwhl.mobile.shared.scaffold.LocalNavAnimatedVisibilityScope
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.LocalAppState
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.HomeTab
import com.adammcneilly.pwhl.mobile.shared.standings.StandingsScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

private val config = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclassesOfSealed<AppScreen>()
        }
    }
}

@Composable
fun AppNavHost() {
    val startDestination = AppScreen.Tab(HomeTab.Feed)

    val backStack = rememberNavBackStack(
        config,
        startDestination,
    )

    val appState = LocalAppState.current

    val currentTab = appState.currentSelectedTab

    LaunchedEffect(currentTab) {
        if (currentTab != null) {
            val previousTab = (backStack.lastOrNull() as? AppScreen.Tab)?.tab
            if (previousTab != null) {
                if (currentTab != previousTab) {
                    // Before adding this tab, drop everything up to the first tab
                    while (backStack.lastOrNull() != startDestination) {
                        backStack.removeLastOrNull()
                    }

                    // Need to navigate to current tab
                    backStack.add(AppScreen.Tab(currentTab))
                }
            }
        }
    }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    val isMediumOrLargerWidth = windowSizeClass.isWidthAtLeastBreakpoint(
        widthDpBreakpoint = WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND,
    )

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()

            // If we're navigating back to a home tab, update app state.
            val newTab = (backStack.lastOrNull() as? AppScreen.Tab)?.tab
            if (newTab != null) {
                appState.onNavItemSelected(newTab)
            }
        },
        sceneStrategies = listOf(
            SinglePaneSceneStrategy(),
            TwoPaneSceneStrategy(
                isMediumOrLargerWidth = isMediumOrLargerWidth,
            ),
        ),
        entryProvider = { key ->
            navEntryProvider(key, backStack)
        },
    )
}

private fun navEntryProvider(
    key: NavKey,
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> {
    val key = key as? AppScreen ?: error("Invalid nav key: $key")
    return when (key) {
        is AppScreen.GameDetail -> {
            gameDetailEntry(key)
        }

        is AppScreen.Tab -> {
            homeTabEntry(
                key = key,
                backStack = backStack,
            )
        }
    }
}

private fun gameDetailEntry(
    key: AppScreen.GameDetail,
): NavEntry<NavKey> {
    return NavEntry(
        key = key,
        metadata = TwoPaneScene.twoPane(),
    ) {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides LocalNavAnimatedContentScope.current,
        ) {
            GameDetailScreen(
                viewModel = koinViewModel(
                    parameters = {
                        parametersOf(key.gameId)
                    },
                ),
            )
        }
    }
}

private fun homeTabEntry(
    key: AppScreen.Tab,
    backStack: NavBackStack<NavKey>,
): NavEntry<NavKey> {
    val metadata = if (key.tab.supportsTwoPane) {
        TwoPaneScene.twoPane()
    } else {
        emptyMap()
    }

    return NavEntry(
        key = key,
        metadata = metadata,
    ) {
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides LocalNavAnimatedContentScope.current,
        ) {
            when (key.tab) {
                HomeTab.Feed -> {
                    FeedScreen(
                        onGameClicked = { gameId ->
                            navigateToGameDetail(gameId, backStack)
                        },
                    )
                }

                HomeTab.News -> {
                    NewsScreen()
                }

                HomeTab.Standings -> {
                    StandingsScreen(
                        onTeamClicked = {},
                    )
                }

                HomeTab.Profile -> {
                    ProfileScreen()
                }
            }
        }
    }
}

private fun navigateToGameDetail(
    gameId: String,
    backStack: NavBackStack<NavKey>,
) {
    val newScreen = AppScreen.GameDetail(gameId)

    if (backStack.lastOrNull() is AppScreen.GameDetail) {
        backStack[backStack.lastIndex] = newScreen
    } else {
        backStack.add(newScreen)
    }
}
