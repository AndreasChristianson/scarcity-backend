package com.pessimisticit.scarcitybackend.distributions

import net.andrewmao.probability.TruncatedNormal
import org.apache.commons.math3.distribution.AbstractRealDistribution
import java.net.URI

data class TruncatedGaussianDistribution(
    val center: Double,
    val stdDev: Double,
    val min: Double = 0.0,
    val max: Double = center + stdDev * 5
) : DistributionConfiguration {
    private lateinit var dist: AbstractRealDistribution

    init {
        require(max > min) {
            "Max must be greater than min"
        }
        dist = TruncatedNormal(
            mean = center,
            sd = stdDev,
            lb = min,
            ub = max,
        )
    }

    override fun roll(): Double {
        return dist.sample()
    }

    override val average: Double
        get() = dist.numericalMean
    override val name: String
        get() = "~${format(average)}"
    override val description: String
        get() = """
            |Between ${format(min)} and ${format(max)} normally distributed 
            |with the center at ${format(center)} and a standard deviation 
            |of ${format(stdDev)}. This will average ${format(average)}.
            |""".trimMargin()
    override val icon: URI
        get() = URI("http://example.com")
}