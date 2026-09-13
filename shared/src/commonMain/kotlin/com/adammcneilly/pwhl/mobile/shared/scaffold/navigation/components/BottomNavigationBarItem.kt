package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.NavItem

@Composable
fun BottomNavigationBarItem(
    navItem: NavItem,
    labelVisible: Boolean,
    modifier: Modifier = Modifier,
) {
    val contentColor = if (navItem.selected) {
        MaterialTheme.colorScheme.primary
    } else {
        LocalContentColor.current
    }

    CompositionLocalProvider(
        LocalContentColor provides contentColor,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 8.dp,
                ),
        ) {
            Icon(
                imageVector = navItem.tab.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp),
            )

            AnimatedVisibility(
                visible = labelVisible,
            ) {
                Text(
                    text = navItem.tab.label,
                )
            }
        }
    }
}
