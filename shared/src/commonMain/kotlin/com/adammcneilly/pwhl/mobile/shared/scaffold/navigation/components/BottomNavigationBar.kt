package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.app.LocalAppState

@Composable
fun BottomNavigationBar(
    tabBarScrollConnection: FloatingTabBarScrollConnection,
    modifier: Modifier = Modifier,
) {
    val appState = LocalAppState.current

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(50),
            )
            .wrapContentHeight(),
    ) {
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
