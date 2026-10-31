package com.adammcneilly.pwhl.mobile.shared.util

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter
import platform.Foundation.NSNumberFormatterDecimalStyle

actual fun numberFormatter(): NumberFormatter {
    return IOSNumberFormatter()
}

private class IOSNumberFormatter : NumberFormatter {
    override fun formatSavePercentage(
        value: Float,
    ): String {
        val numberFormatter = NSNumberFormatter()
        numberFormatter.numberStyle = NSNumberFormatterDecimalStyle
        numberFormatter.minimumFractionDigits = 0.toULong()
        numberFormatter.maximumFractionDigits = 3.toULong()
        return numberFormatter.stringFromNumber(NSNumber(value.toFloat())).orEmpty()
    }
}
