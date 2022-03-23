package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.formatting.EnumFormatter
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI

enum class DamageType(val icon: SvgIcon) {
    PHYSICAL(SvgIcon(URI("$svgRoot/fist.svg"), "brown")),
    FROST(SvgIcon(URI("$svgRoot/snowflake-1.svg"), "lightblue")),
    FIRE(SvgIcon(URI("$svgRoot/flame.svg"), "orangered")),
    ;

    override fun toString(): String {
        return EnumFormatter.toDisplayString(this)
    }
}