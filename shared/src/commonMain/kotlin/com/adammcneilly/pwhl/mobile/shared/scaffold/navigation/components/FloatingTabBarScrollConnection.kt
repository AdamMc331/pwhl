package com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

@Composable
fun rememberFloatingTabBarScrollConnection(): FloatingTabBarScrollConnection {
    return remember {
        FloatingTabBarScrollConnection()
    }
}

class FloatingTabBarScrollConnection : NestedScrollConnection {
    var isCollapsed by mutableStateOf(false)
        private set

    fun expand() {
        isCollapsed = false
    }

    fun collapse() {
        isCollapsed = true
    }

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource,
    ): Offset {
        if (available.y < 0 && !isCollapsed) {
            isCollapsed = true
        } else if (available.y > 0 && isCollapsed) {
            isCollapsed = false
        }

        return Offset.Zero
    }
}
