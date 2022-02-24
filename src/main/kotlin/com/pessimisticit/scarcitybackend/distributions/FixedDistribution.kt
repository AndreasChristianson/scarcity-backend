package com.pessimisticit.scarcitybackend.distributions

import com.pessimisticit.scarcitybackend.formatting.NumberFormatter.formatDecimal
import java.net.URI

data class FixedDistribution(val fixed: Double) : DistributionConfiguration {
    override fun roll(): Double {
        return fixed
    }

    override val average: Double
        get() = fixed
    override val name: String
        get() = formatDecimal(fixed)
    override val description: String
        get() = "Exactly ${formatDecimal(fixed)}, every time."
    override val icon: URI
        get() = URI("http://example.com")
}