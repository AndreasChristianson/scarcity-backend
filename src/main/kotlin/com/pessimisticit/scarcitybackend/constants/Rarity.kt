package com.pessimisticit.scarcitybackend.constants

enum class Rarity(
    val relativeWeight: Int,
    val budgetMultiplier: Double,
) {
    COMMON(1000, 1.0),
    UNCOMMON(400, 1.02),
    SCARCE(100, 1.04),
    RARE(10, 1.08),
    EXTRAORDINARY(1, 1.16),
    NEVER(0, 1.0),
}
