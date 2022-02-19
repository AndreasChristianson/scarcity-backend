package com.pessimisticit.scarcitybackend.distributions

import com.pessimisticit.scarcitybackend.interfaces.Displayable
import java.text.DecimalFormat

interface DistributionConfiguration : Displayable {
    fun roll(): Double
    val average: Double
    fun format(value: Double): String {
        return df.format(value)
    }

    companion object {
        private val df: DecimalFormat = DecimalFormat("0.##")
    }
}