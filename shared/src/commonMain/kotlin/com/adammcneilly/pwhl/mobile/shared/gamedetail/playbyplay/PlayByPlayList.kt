package com.adammcneilly.pwhl.mobile.shared.gamedetail.playbyplay

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent
import com.adammcneilly.pwhl.mobile.shared.ui.components.PlayByPlayMap
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
@OptIn(ExperimentalFoundationApi::class)
fun PlayByPlayList(
    events: Map<String, List<PlayByPlayEventDisplayModel>>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        @Suppress("DpUsageRule")
        PlayByPlayMap(
            events = events.flatMap { it.value }.filter {
                it.type == PlayByPlayEvent.Type.SHOT || it.type == PlayByPlayEvent.Type.GOAL
            },
            modifier = Modifier
                .widthIn(max = 470.dp)
                .height(200.dp)
                .padding(PWHLTheme.dimensions.componentPadding),
        )

        LazyColumn(
            contentPadding = WindowInsets.navigationBars.asPaddingValues(),
        ) {
            events.forEach { (periodName, pbpEvents) ->
                stickyHeader {
                    Text(
                        text = periodName,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.secondaryContainer,
                            )
                            .padding(PWHLTheme.dimensions.componentPadding),
                    )
                }

                itemsIndexed(pbpEvents) { index, event ->
                    PlayByPlayListItem(event)

                    if (index != pbpEvents.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}
