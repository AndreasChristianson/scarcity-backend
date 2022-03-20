package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.formatting.EnumFormatter
import java.net.URI

enum class DamageType(val icon: URI) {
    PHYSICAL(URI("http://example.com")),
    FROST(URI("http://example.com")),
    FIRE(URI("http://example.com")),
    ;

    override fun toString(): String {
        return EnumFormatter.toDisplayString(this)
    }
}