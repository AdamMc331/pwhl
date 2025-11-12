package com.adammcneilly.pwhl.mobile.shared

import com.adammcneilly.pwhl.mobile.shared.displaymodels.ImageDisplayModel

/**
 * Helper object that will provide a local image resource for a specific
 * team id.
 */
object LocalTeamImageProvider {
    fun getTeamImage(
        teamId: String,
    ): ImageDisplayModel {
        val res = when (teamId) {
            "1" -> Res.drawable.boston
            "2" -> Res.drawable.minnesota
            "3" -> Res.drawable.montreal
            "4" -> Res.drawable.newyork
            "5" -> Res.drawable.ottawa
            "6" -> Res.drawable.toronto
            "8" -> Res.drawable.seattle
            "9" -> Res.drawable.vancouver
            else -> Res.drawable.pwhl
        }

        return ImageDisplayModel.Local(res)
    }
}
