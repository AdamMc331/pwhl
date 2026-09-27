package com.adammcneilly.pwhl.detekt.rules

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.test.TestConfig
import io.gitlab.arturbosch.detekt.test.assertThat
import io.gitlab.arturbosch.detekt.test.lint
import kotlin.test.Test

class DpUsageRuleTest {
    private val subject = DpUsageRule(Config.empty)

    @Test
    fun `fail file with int dp extension`() {
        val findings = subject.lint(codeWithIntExtension)
        assertThat(findings).hasSize(1)
    }

    @Test
    fun `fail file with float dp extension`() {
        val findings = subject.lint(codeWithFloatExtension)
        assertThat(findings).hasSize(1)
    }

    @Test
    fun `pass file without dp extension`() {
        val findings = subject.lint(codeWithDimensions)
        assertThat(findings).hasSize(0)
    }

    @Test
    fun `pass file with excluded dp extension`() {
        val config = TestConfig(
            "ignoreValues" to listOf("1"),
        )
        val subject = DpUsageRule(config)
        val findings = subject.lint(codeWithIntExtension)
        assertThat(findings).hasSize(0)
    }
}

private val codeWithIntExtension: String = """
    import androidx.compose.ui.unit.dp
    
    val myWidth = 1.dp
""".trimIndent()

private val codeWithFloatExtension: String = """
    import androidx.compose.ui.unit.dp
    
    val myWidth = 4.0.dp
""".trimIndent()

private val codeWithDimensions: String = """
    val myWidth = PWHLTheme.dimensions.itemWidth
""".trimIndent()
