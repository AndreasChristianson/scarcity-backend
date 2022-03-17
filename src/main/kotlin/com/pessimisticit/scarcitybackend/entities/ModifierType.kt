package com.pessimisticit.scarcitybackend.entities

enum class ModifierType(
    val baseLevel: Double
) {
    HELPFUL(1.0),
    NEUTRAL(0.0),
    HARMFUL(-1.0),
}
