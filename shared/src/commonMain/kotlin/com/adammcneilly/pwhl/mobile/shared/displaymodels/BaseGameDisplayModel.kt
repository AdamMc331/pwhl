package com.adammcneilly.pwhl.mobile.shared.displaymodels

interface BaseGameDisplayModel {
    val id: String
    val status: String
    val dateString: String

    val statusSharedElementKey
        get() = "game_${id}_status"

    val headerSharedElementKey
        get() = "game_${id}_header"
}
