package com.pessimisticit.scarcitybackend.distributions

interface DistributionConfiguration {
    fun roll(): Double
    val average: Double
    val distribution: String
}

