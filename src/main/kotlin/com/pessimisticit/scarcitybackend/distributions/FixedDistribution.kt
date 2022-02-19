package com.pessimisticit.scarcitybackend.distributions

import java.net.URI

data class FixedDistribution(val fixed: Double) : DistributionConfiguration {
    override fun roll(): Double {
        return fixed
    }

    override val average: Double
        get() = fixed
    override val name: String
        get() = format(fixed)
    override val description: String
        get() = "Exactly ${format(fixed)} every time."
    override val icon: URI
        get() = URI("http://example.com")
}