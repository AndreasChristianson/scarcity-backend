package com.pessimisticit.scarcitybackend.distributions

interface DistributionConfiguration {
    fun roll(): Double
    fun scale(damageMultiplier: Double)

    val average: Double
    val distribution: String
}

