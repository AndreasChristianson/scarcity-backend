package com.pessimisticit.scarcitybackend.constants

enum class DamageShape(val relativeSpread: Double) {
    PIERCING(0.2),
    SLASHING(0.1),
    BLUDGEONING(0.35),
    CHOPPING(0.25),
    ENVELOPING(0.4),
    PROXIMITY(0.05),
    CONVECTION(0.3),
}