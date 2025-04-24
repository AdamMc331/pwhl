package com.adammcneilly.pwhl.mobile.shared

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.adammcneilly.pwhl.mobile.shared.feed.FeedScreen
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailScreen
import com.adammcneilly.pwhl.mobile.shared.gamedetail.GameDetailViewModel
import com.adammcneilly.pwhl.mobile.shared.news.NewsScreen
import com.adammcneilly.pwhl.mobile.shared.profile.ProfileScreen
import com.adammcneilly.pwhl.mobile.shared.standings.StandingsScreen
import com.adammcneilly.pwhl.mobile.shared.teamdetail.TeamDetailScreen
import com.adammcneilly.pwhl.mobile.shared.teamdetail.TeamDetailViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalSharedTransitionApi::class)
val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope> {
    throw IllegalStateException("Local SharedTransitionScope required")
}

val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope> {
    throw IllegalStateException("Local AnimatedVisibilityScope required")
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    SharedTransitionLayout {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
        ) {
            NavHost(
                navController = navController,
                startDestination = FeedScreen,
                modifier = modifier,
            ) {
                animatedComposable<FeedScreen> {
                    FeedScreen(
                        onGameClicked = { gameId ->
                            navController.navigate(GameDetailScreen(gameId))
                        },
                    )
                }

                animatedComposable<NewsScreen> {
                    NewsScreen()
                }

                animatedComposable<StandingsScreen> {
                    StandingsScreen(
                        onTeamClicked = { teamId ->
                            navController.navigate(TeamDetailScreen(teamId))
                        },
                    )
                }

                animatedComposable<ProfileScreen> {
                    ProfileScreen()
                }

                animatedComposable<GameDetailScreen> { backStackEntry ->
                    val screen: GameDetailScreen = backStackEntry.toRoute()

                    val viewModel: GameDetailViewModel = koinViewModel(
                        parameters = {
                            parametersOf(screen.gameId)
                        },
                    )

                    GameDetailScreen(
                        viewModel = viewModel,
                    )
                }

                animatedComposable<TeamDetailScreen> { backStackEntry ->
                    val screen: TeamDetailScreen = backStackEntry.toRoute()

                    val viewModel: TeamDetailViewModel = koinViewModel(
                        parameters = {
                            parametersOf(screen.teamId)
                        },
                    )

                    TeamDetailScreen(
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}

private inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit,
) {
    composable<T> { backStackEntry ->
        CompositionLocalProvider(
            LocalNavAnimatedVisibilityScope provides this,
        ) {
            content(backStackEntry)
        }
    }
}
