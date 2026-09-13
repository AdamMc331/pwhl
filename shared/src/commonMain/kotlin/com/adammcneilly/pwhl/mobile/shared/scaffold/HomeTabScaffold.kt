package com.adammcneilly.pwhl.mobile.shared.scaffold

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationBar
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationRail

/**
 * A wrapper around [PersistentScaffold] that provides pre-defined defaults
 * useful for home tab screens.
 */
@Composable
@Suppress("LongParameterList")
@OptIn(ExperimentalSharedTransitionApi::class)
fun ScaffoldState.HomeTabScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable ScaffoldState.() -> Unit = {},
    floatingActionButton: @Composable ScaffoldState.() -> Unit = {},
    navigationBar: @Composable ScaffoldState.() -> Unit = {
        PersistentNavigationBar(
            modifier = Modifier
                .animateEnterExit(
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                ),
        )
    },
    navigationRail: @Composable ScaffoldState.() -> Unit = {
        PersistentNavigationRail()
    },
    toastMessage: @Composable ScaffoldState.() -> Unit = {},
    content: @Composable ScaffoldState.(PaddingValues) -> Unit,
) {
    PersistentScaffold(
        modifier = modifier,
        topBar = topBar,
        floatingActionButton = floatingActionButton,
        navigationBar = navigationBar,
        navigationRail = navigationRail,
        toastMessage = toastMessage,
        content = content,
    )
}
