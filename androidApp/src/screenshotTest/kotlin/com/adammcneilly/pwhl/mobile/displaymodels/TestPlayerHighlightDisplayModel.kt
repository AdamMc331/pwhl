package com.adammcneilly.pwhl.mobile.displaymodels

import com.adammcneilly.pwhl.mobile.shared.displaymodels.PlayerHighlightDisplayModel
import com.adammcneilly.pwhl.mobile.shared.displaymodels.StatDisplayModel

val testPlayerHighlightDisplayModel = PlayerHighlightDisplayModel(
    team = testSirensDisplayModel,
    player = testPlayerDisplayModel,
    highlightStats = listOf(
        StatDisplayModel(
            shortCode = "G",
            fullName = "Goals",
            value = "1",
        ),
        StatDisplayModel(
            shortCode = "A",
            fullName = "Assists",
            value = "1",
        ),
        StatDisplayModel(
            shortCode = "P",
            fullName = "Points",
            value = "2",
        ),
    ),
)
