package com.adammcneilly.pwhl.mobile.shared.profile

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.navigation.LocalSceneType
import com.adammcneilly.pwhl.mobile.shared.navigation.SceneType
import com.adammcneilly.pwhl.mobile.shared.scaffold.PersistentScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationBar
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.PersistentNavigationRail
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().PersistentScaffold(
        modifier = modifier,
        navigationBar = {
            PersistentNavigationBar(
                modifier = Modifier
                    .animateEnterExit(
                        enter = slideInVertically(initialOffsetY = { it }),
                        exit = slideOutVertically(targetOffsetY = { it }),
                    ),
            )
        },
        navigationRail = {
            if (LocalSceneType.current != SceneType.TwoPane) {
                PersistentNavigationRail()
            }
        },
        content = { scaffoldPadding ->
            Text(
                text = "Profile Content Stub",
                modifier = Modifier
                    .padding(scaffoldPadding),
            )
        },
    )
}
