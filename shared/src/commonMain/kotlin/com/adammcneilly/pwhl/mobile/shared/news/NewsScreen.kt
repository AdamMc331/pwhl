package com.adammcneilly.pwhl.mobile.shared.news

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.adammcneilly.pwhl.mobile.shared.scaffold.HomeTabScaffold
import com.adammcneilly.pwhl.mobile.shared.scaffold.rememberScaffoldState

@Composable
fun NewsScreen(
    modifier: Modifier = Modifier,
) {
    rememberScaffoldState().HomeTabScaffold(
        modifier = modifier,
        content = { scaffoldPadding ->
            Text(
                text = "News Content Stub",
                modifier = Modifier
                    .padding(scaffoldPadding),
            )
        },
    )
}
