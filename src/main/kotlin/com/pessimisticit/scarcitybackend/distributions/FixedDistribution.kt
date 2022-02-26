package com.pessimisticit.scarcitybackend.distributions

data class FixedDistribution(val fixed: Double) : DistributionConfiguration {
    override fun roll(): Double {
        return fixed
    }

    override val average: Double
        get() = fixed
    override val distribution: String
        get() = "Fixed"
}