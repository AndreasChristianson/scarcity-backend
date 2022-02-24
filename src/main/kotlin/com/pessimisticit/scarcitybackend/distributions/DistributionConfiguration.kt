package com.pessimisticit.scarcitybackend.distributions

import com.pessimisticit.scarcitybackend.interfaces.Displayable

interface DistributionConfiguration : Displayable {
    fun roll(): Double
    val average: Double
}

