package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.formatting.EnumFormatter
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI

enum class DamageShape(
    private val relativeSpread: Double,
    val icon:SvgIcon
) {
    PIERCING(0.2, SvgIcon(URI("$svgRoot/pierced-body.svg"))),
    SLASHING(0.1, SvgIcon(URI("$svgRoot/crossed-slashes.svg"))),
    BLUDGEONING(0.35, SvgIcon(URI("$svgRoot/hammer-break.svg"))),
    CHOPPING(0.25, SvgIcon(URI("$svgRoot/arm-sling.svg"))),
    ENVELOPING(0.4, SvgIcon(URI("$svgRoot/burning-passion.svg"))),
    PROXIMITY(0.05, SvgIcon(URI("$svgRoot/burning-dot.svg"))),
    CONVECTION(0.3, SvgIcon(URI("$svgRoot/fire-breath.svg"))), ;

    fun calculateSpread(damage: Double): Double {
        return relativeSpread * damage
    }

    override fun toString(): String {
        return EnumFormatter.toDisplayString(this)
    }

}