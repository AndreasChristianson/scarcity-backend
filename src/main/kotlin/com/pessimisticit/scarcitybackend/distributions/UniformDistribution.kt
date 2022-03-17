package com.pessimisticit.scarcitybackend.distributions

import kotlin.random.Random

class UniformDistribution(
    var min: Double,
    var max: Double
) : DistributionConfiguration {
    init {
        require(max > min) {
            "Max must be greater than min"
        }
    }

    override fun roll(): Double {
        return Random.nextDouble(min, max)
    }

    override fun scale(damageMultiplier: Double) {
        min *= damageMultiplier
        max *= damageMultiplier
    }

    override val average: Double
        get() = max - min

    override val distribution: String
        get() = "Uniform"
}