package com.adammcneilly.pwhl.mobile.shared.gamedetail.playbyplay

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayByPlayEventDisplayModel
import com.adammcneilly.pwhl.mobile.shared.models.playbyplay.PlayByPlayEvent
import com.adammcneilly.pwhl.mobile.shared.ui.components.ImageWrapper
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLColors
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun PlayByPlayListItem(
    event: PlayByPlayEventDisplayModel,
    modifier: Modifier = Modifier,
) {
    PWHLTheme(
        seedColor = themeSeedColor(event),
    ) {
        Surface(
            color = containerColor(event),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingDefault),
                modifier = modifier
                    .padding(PWHLTheme.dimensions.componentPadding),
            ) {
                TeamImage(event)

                EventInfo(
                    event = event,
                    modifier = Modifier
                        .weight(1F),
                )

                Text(
                    text = event.time,
                )
            }
        }
    }
}

@Composable
private fun EventInfo(
    event: PlayByPlayEventDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = event.title,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = event.description,
        )
    }
}

@Composable
private fun TeamImage(
    event: PlayByPlayEventDisplayModel,
) {
    ImageWrapper(
        image = event.teamImage,
        contentDescription = null,
        modifier = Modifier
            .size(PWHLTheme.dimensions.imageSizeCompact),
    )
}

@Composable
private fun containerColor(
    event: PlayByPlayEventDisplayModel,
): Color {
    return when (event.type) {
        PlayByPlayEvent.Type.GOAL -> {
            MaterialTheme.colorScheme.primaryContainer
        }

        else -> {
            MaterialTheme.colorScheme.surface
        }
    }
}

/**
 * For goals, PBP events are highlighted based on the color of the team.
 * For everything else, we can use the default purple.
 */
private fun themeSeedColor(
    event: PlayByPlayEventDisplayModel,
): Color {
    return when (event.type) {
        PlayByPlayEvent.Type.GOAL -> {
            PWHLColors.fromTeamId(event.teamId)
        }

        else -> {
            PWHLColors.Purple
        }
    }
}
