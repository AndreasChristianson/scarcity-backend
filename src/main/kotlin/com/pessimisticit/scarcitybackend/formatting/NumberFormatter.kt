package com.pessimisticit.scarcitybackend.formatting

import java.text.DecimalFormat

object NumberFormatter {
    fun formatDecimal(value: Double): String {
        return decimalFormat.format(value)
    }

    private val decimalFormat: DecimalFormat = DecimalFormat("0.##")

    fun formatPercent(value: Double): String {
        return percentFormat.format(value)
    }

    private val percentFormat: DecimalFormat = DecimalFormat("0.##%")

    fun formatGameDuration(value: Long): String {
        return "${value}ms"
    }
}