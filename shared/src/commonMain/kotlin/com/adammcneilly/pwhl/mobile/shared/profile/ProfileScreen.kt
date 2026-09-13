package com.adammcneilly.pwhl.mobile.shared.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.HomeTabScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.navigation.components.rememberFloatingTabBarScrollConnection
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().HomeTabScaffold(
        tabBarScrollConnection = rememberFloatingTabBarScrollConnection(),
        modifier = modifier,
        content = { scaffoldPadding ->
            Text(
                text = "Profile Content Stub",
                modifier = Modifier
                    .padding(scaffoldPadding),
            )
        },
    )
}
