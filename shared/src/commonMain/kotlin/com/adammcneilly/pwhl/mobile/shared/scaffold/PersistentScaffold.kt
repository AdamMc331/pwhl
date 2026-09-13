package com.adammcneilly.pwhl.mobile.shared.scaffold

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateBounds
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.plus
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
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
            var navBarHeightDp by remember {
                mutableStateOf(0.dp)
            }

            val density = LocalDensity.current

            Surface(
                modifier = modifier
                    .animateBounds(lookaheadScope = this),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                ) {
                    val navBarAwarePadding = PaddingValues(
                        bottom = navBarHeightDp + 12.dp,
                    )

                    content(WindowInsets.statusBars.asPaddingValues().plus(navBarAwarePadding))

                    navigationBar(
                        tabBarScrollConnection,
                        Modifier
                            .onSizeChanged { size ->
                                with(density) {
                                    navBarHeightDp = size.height.toDp()
                                    println("ADAMLOG - NB HEIGHT: $navBarHeightDp")
                                }
                            }
                            .align(Alignment.BottomCenter)
                            .navigationBarsPadding()
                            .padding(24.dp),
                    )
                }
            }
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
