package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.formatting.EnumFormatter

enum class DamageShape(private val relativeSpread: Double) {
    PIERCING(0.2),
    SLASHING(0.1),
    BLUDGEONING(0.35),
    CHOPPING(0.25),
    ENVELOPING(0.4),
    PROXIMITY(0.05),
    CONVECTION(0.3), ;

    fun calculateSpread(damage: Double): Double {
        return relativeSpread * damage
    }

    override fun toString(): String {
        return EnumFormatter.toDisplayString(this)
    }

}