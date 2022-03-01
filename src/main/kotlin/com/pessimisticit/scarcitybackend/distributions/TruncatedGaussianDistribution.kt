package com.pessimisticit.scarcitybackend.distributions

import net.andrewmao.probability.TruncatedNormal
import org.apache.commons.math3.distribution.AbstractRealDistribution

class TruncatedGaussianDistribution(
    var center: Double,
    val stdDev: Double,
    val min: Double = 0.0,
    val max: Double = center + stdDev * 5
) : DistributionConfiguration {
    private var dist: AbstractRealDistribution

    init {
        require(max > min) {
            "Max must be greater than min"
        }
        dist = initDist()
    }

    override fun roll(): Double {
        return dist.sample()
    }

    override fun scale(damageMultiplier: Double) {
        center *= damageMultiplier
        dist = initDist()
    }

    private fun initDist() = TruncatedNormal(
        mean = center,
        sd = stdDev,
        lb = min,
        ub = max,
    )


    override val average: Double
        get() = dist.numericalMean

    override val distribution: String
        get() = "Truncated Gaussian"
}