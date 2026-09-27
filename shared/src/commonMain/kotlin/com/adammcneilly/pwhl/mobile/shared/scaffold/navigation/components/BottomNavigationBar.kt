package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.LocalAppState

@Composable
fun BottomNavigationBar(
    tabBarScrollConnection: FloatingTabBarScrollConnection,
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = CircleShape,
        modifier = modifier
            .wrapContentHeight(),
    ) {
        Row {
            appState.navItems.forEach { item ->
                BottomNavigationBarItem(
                    navItem = item,
                    labelVisible = !tabBarScrollConnection.isCollapsed,
                    modifier = Modifier
                        .clickable {
                            appState.onNavItemSelected(item.tab)
                        }
                        .weight(1F),
                )
            }
        }
    }
}
