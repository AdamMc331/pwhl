package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.LocalAppState

@Composable
fun SideNavigationRail(
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current

    NavigationRail(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            appState.navItems.forEach { item ->
                NavigationBarItem(
                    navItem = item,
                    labelVisible = true,
                    modifier = Modifier
                        .clickable {
                            appState.onNavItemSelected(item.tab)
                        },
                )
            }
        }
    }
}
