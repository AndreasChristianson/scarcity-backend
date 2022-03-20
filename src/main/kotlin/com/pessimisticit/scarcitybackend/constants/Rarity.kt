package com.pessimisticit.scarcitybackend.constants

enum class Rarity(
    val relativeWeight: Double,
) {
    NEVER(0.0),
    COMMON(1000.0),
    UNCOMMON(400.0),
    SCARCE(100.0),
    RARE(10.0),
    EXTRAORDINARY(1.0),
}
