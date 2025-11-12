@file:Suppress("MagicNumber")

package com.adammcneilly.pwhl.mobile.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.window.core.layout.WindowSizeClass
import com.adammcneilly.pwhl.mobile.shared.displaymodels.ImageDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayerHighlightDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.StatDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun PlayerHighlightCard(
    playerHighlight: PlayerHighlightDisplayModel,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
) {
    Card(
        shape = shape,
        modifier = modifier
            .width(IntrinsicSize.Max),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.componentVerticalPadding),
            modifier = Modifier
                .padding(PWHLTheme.dimensions.componentPadding),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.componentHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                PlayerImage(playerHighlight)

                PlayerInfo(playerHighlight)
            }

            Stats(playerHighlight)
        }
    }
}

@Composable
private fun Stats(
    playerHighlight: PlayerHighlightDisplayModel,
) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingCompact),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = PWHLTheme.dimensions.itemSpacingCompact,
                    vertical = PWHLTheme.dimensions.itemSpacingUltraCompact,
                ),
        ) {
            playerHighlight.highlightStats.forEach { stat ->
                StatItem(
                    stat = stat,
                    modifier = Modifier
                        .weight(1F),
                )
            }
        }
    }
}

@Composable
private fun StatItem(
    stat: StatDisplayModel,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        val statText = if (currentWindowAdaptiveInfo().windowSizeClass.isWidthAtLeastBreakpoint(
                WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND,
            )
        ) {
            stat.fullName
        } else {
            stat.shortCode
        }

        Text(
            text = statText,
            style = MaterialTheme.typography.titleMedium,
        )

        Text(
            text = stat.value,
        )
    }
}

@Composable
private fun PlayerInfo(
    playerHighlight: PlayerHighlightDisplayModel,
) {
    Column {
        PlayerName(playerHighlight)

        PlayerSubtitle(playerHighlight)
    }
}

@Composable
private fun PlayerName(
    playerHighlight: PlayerHighlightDisplayModel,
) {
    val imageSize = PWHLTheme.dimensions.imageSizeDefault

    val fontSize = with(LocalDensity.current) {
        imageSize.div(3).toSp()
    }

    val textStyle = MaterialTheme.typography.titleSmall.copy(
        fontSize = fontSize,
        lineHeight = fontSize.times(1.25),
    )

    Text(
        text = playerHighlight.player.fullName,
        style = textStyle,
    )
}

@Composable
private fun PlayerSubtitle(
    playerHighlight: PlayerHighlightDisplayModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingUltraCompact),
    ) {
        val imageSize = PWHLTheme.dimensions.imageSizeDefault

        val fontSizeDp = imageSize.div(4)

        val fontSizeSp = with(LocalDensity.current) {
            fontSizeDp.toSp()
        }

        val textStyle = LocalTextStyle.current.copy(
            fontSize = fontSizeSp,
            lineHeight = fontSizeSp.times(1.25),
        )

        ImageWrapper(
            image = playerHighlight.team.image,
            contentDescription = playerHighlight.team.name,
            modifier = Modifier
                .size(fontSizeDp),
        )

        val subtitle = "#${playerHighlight.player.jerseyNumber} • ${playerHighlight.player.position}"

        Text(
            text = subtitle,
            style = textStyle,
        )
    }
}

@Composable
private fun PlayerImage(
    playerHighlight: PlayerHighlightDisplayModel,
) {
    ImageWrapper(
        image = playerHighlight.player.playerImage ?: ImageDisplayModel.Placeholder,
        contentDescription = playerHighlight.player.fullName,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(PWHLTheme.dimensions.imageSizeDefault)
            .clip(CircleShape),
    )
}
