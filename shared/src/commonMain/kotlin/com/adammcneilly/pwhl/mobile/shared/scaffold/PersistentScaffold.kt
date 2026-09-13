package com.adammcneilly.pwhl.mobile.shared.scaffold

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateBounds
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.FloatingTabBarScrollConnection
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

/**
 * A persistent [Scaffold] that will render the various slots for any given screen,
 * allowing each screen to hold persistent UI elements such as [com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationBar]
 * or [com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationRail].
 */
@Composable
@Suppress("LongParameterList")
@OptIn(ExperimentalSharedTransitionApi::class)
fun ScaffoldState.PersistentScaffold(
    modifier: Modifier = Modifier,
    tabBarScrollConnection: FloatingTabBarScrollConnection = FloatingTabBarScrollConnection(),
    topBar: @Composable ScaffoldState.() -> Unit = {},
    floatingActionButton: @Composable ScaffoldState.() -> Unit = {},
    navigationBar: @Composable ScaffoldState.(FloatingTabBarScrollConnection, Modifier) -> Unit = { _, _ -> },
    navigationRail: @Composable ScaffoldState.() -> Unit = {},
    toastMessage: @Composable ScaffoldState.() -> Unit = {},
    content: @Composable ScaffoldState.(PaddingValues) -> Unit,
) {
    NavigationRailScaffold(
        modifier = modifier,
        navigationRail = navigationRail,
        content = {
            Scaffold(
                modifier = modifier
                    .animateBounds(lookaheadScope = this),
                topBar = {
                    topBar()
                },
                floatingActionButton = {
                    floatingActionButton()
                },
                bottomBar = {},
                snackbarHost = {
                    toastMessage()
                },
                content = { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                    ) {
                        content(paddingValues)

                        navigationBar(
                            tabBarScrollConnection,
                            Modifier
                                .align(Alignment.BottomCenter)
                                .padding(24.dp),
                        )
                    }
                },
            )
        },
    )
}

@Composable
private inline fun ScaffoldState.NavigationRailScaffold(
    modifier: Modifier = Modifier,
    navigationRail: @Composable ScaffoldState.() -> Unit,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier,
        content = {
            Box(
                modifier = Modifier
                    .widthIn(max = PWHLTheme.dimensions.navRailWidth)
                    .zIndex(2F),
            ) {
                navigationRail()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .zIndex(1F),
            ) {
                content()
            }
        },
    )
}
