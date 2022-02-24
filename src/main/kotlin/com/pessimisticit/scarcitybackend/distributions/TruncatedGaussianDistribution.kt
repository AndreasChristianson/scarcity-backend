package com.pessimisticit.scarcitybackend.distributions

import com.pessimisticit.scarcitybackend.formatting.NumberFormatter.formatDecimal
import net.andrewmao.probability.TruncatedNormal
import org.apache.commons.math3.distribution.AbstractRealDistribution
import java.net.URI

data class TruncatedGaussianDistribution(
    val center: Double,
    val stdDev: Double,
    val min: Double = 0.0,
    val max: Double = center + stdDev * 5
) : DistributionConfiguration {
    private var dist: AbstractRealDistribution

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
        get() = "~${formatDecimal(average)}"
    override val description: String
        get() = """
            |Between ${formatDecimal(min)} and ${formatDecimal(max)} normally distributed 
            |with the center at ${formatDecimal(center)} and a standard deviation 
            |of ${formatDecimal(stdDev)}. This will average ${formatDecimal(average)}.
            |""".trimMargin()
    override val icon: URI
        get() = URI("http://example.com")
}