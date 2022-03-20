package com.pessimisticit.scarcitybackend.distributions

class FixedDistribution(var fixed: Double) : DistributionConfiguration {
    override fun roll(): Double {
        return fixed
    }

    override fun scale(damageMultiplier: Double) {
        fixed *= damageMultiplier
    }

    override val average: Double
        get() = fixed
    override val distribution: String
        get() = "Fixed"
}