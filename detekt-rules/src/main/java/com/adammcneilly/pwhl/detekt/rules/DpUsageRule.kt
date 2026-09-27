package com.adammcneilly.pwhl.detekt.rules

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import io.gitlab.arturbosch.detekt.api.config
import org.jetbrains.kotlin.psi.KtConstantExpression
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression

class DpUsageRule(
    config: Config,
) : Rule(config) {
    override val issue = Issue(
        id = javaClass.simpleName,
        severity = Severity.CodeSmell,
        description = "Using dp conversion on constants is discouraged. " +
            "Use dimensions from the PWHLTheme instead.",
        debt = Debt.FIVE_MINS,
    )

    private val ignoreValues by config(emptyList<String>())

    override fun visitDotQualifiedExpression(
        expression: KtDotQualifiedExpression,
    ) {
        val isConstant = expression.receiverExpression is KtConstantExpression
        val isDpConversion = expression.selectorExpression?.text == "dp"
        val isIgnored = (expression.receiverExpression.text in ignoreValues)

        if (isConstant && isDpConversion && !isIgnored) {
            report(CodeSmell(issue, Entity.from(expression), issue.description))
        }

        super.visitDotQualifiedExpression(expression)
    }
}
