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
    override val name: String
        get() = "${formatDecimal(min)} - ${formatDecimal(max)}"
    override val description: String
        get() = "Between ${formatDecimal(min)} and ${formatDecimal(max)} uniformly distributed."
    override val icon: URI
        get() = URI("http://example.com")
}