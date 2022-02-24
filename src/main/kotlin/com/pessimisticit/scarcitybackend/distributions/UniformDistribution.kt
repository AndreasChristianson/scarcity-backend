package com.pessimisticit.scarcitybackend.distributions

import com.pessimisticit.scarcitybackend.formatting.NumberFormatter.formatDecimal
import java.net.URI
import kotlin.random.Random

data class UniformDistribution(val min: Double, val max: Double) : DistributionConfiguration {
    init {
        require(max > min) {
            "Max must be greater than min"
        }
    }

    override fun roll(): Double {
        return Random.nextDouble(min, max)
    }

    override val average: Double
        get() = max - min

    override val distribution: String
        get() = "Uniform"
}