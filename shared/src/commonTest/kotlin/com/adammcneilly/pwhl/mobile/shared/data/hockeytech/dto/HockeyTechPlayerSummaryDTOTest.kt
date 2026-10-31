package com.adammcneilly.pwhl.mobile.shared.data.hockeytech.dto

import com.varabyte.truthish.assertThat
import kotlin.test.Test

class HockeyTechPlayerSummaryDTOTest {
    @Test
    fun `prepend image url with https if not included`() {
        val dto = HockeyTechPlayerSummaryDTO(
            playerImageURL = "test",
        )

        val player = dto.parsePlayer()

        assertThat(player.playerImageURL).isEqualTo("https://test")
    }

    @Test
    fun `skip prepend image url with https if included`() {
        val dto = HockeyTechPlayerSummaryDTO(
            playerImageURL = "https://test",
        )

        val player = dto.parsePlayer()

        assertThat(player.playerImageURL).isEqualTo("https://test")
    }
}
