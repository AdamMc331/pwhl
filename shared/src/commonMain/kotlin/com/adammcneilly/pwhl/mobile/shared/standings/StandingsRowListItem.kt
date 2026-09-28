package com.adammcneilly.pwhl.mobile.shared.standings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import com.adammcneilly.pwhl.mobile.shared.displaymodels.StandingsRowDisplayModel
import com.adammcneilly.pwhl.mobile.shared.ui.components.ImageWrapper
import com.adammcneilly.pwhl.mobile.shared.ui.theme.PWHLTheme

@Composable
fun StandingsRowListItem(
    standingsRow: StandingsRowDisplayModel,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PWHLTheme.dimensions.itemSpacingCompact),
        modifier = modifier
            .padding(PWHLTheme.dimensions.componentPadding),
    ) {
        Rank(
            rank = standingsRow.rank.toString(),
        )

        ImageWrapper(
            image = standingsRow.team.image,
            contentDescription = null,
            modifier = Modifier
                .size(PWHLTheme.dimensions.imageSizeCompact),
        )

        Column {
            Text(
                text = standingsRow.team.name,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = standingsRow.record,
            )
        }

        Spacer(
            modifier = Modifier
                .weight(1F),
        )

        Text(
            text = "${standingsRow.points} Points",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
        )
    }
}

/**
 * Uses a custom [androidx.compose.ui.text.TextMeasurer] to ensure the rank text
 * can always fit two characters for double-digit teams, and the rows will stay aligned.
 */
@Composable
private fun Rank(
    rank: String,
) {
    val style = MaterialTheme.typography.titleLarge

    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    val twoCharWidth = with(density) {
        textMeasurer.measure(
            text = "99",
            style = style,
        ).size.width.toDp()
    }

    Text(
        text = rank,
        style = style,
        modifier = Modifier
            .width(twoCharWidth),
    )
}
