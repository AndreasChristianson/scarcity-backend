package com.pessimisticit.scarcitybackend.constants

import kotlin.math.max

enum class Classification {
    CORRODED,
    MUNDANE,
    IDEAL,
    ENCHANTED,
    ARTIFACT,
//    UNIQUE,
    ;
}

fun getForModificationLevel(sum: Double, max: Double): Classification {
    val target = max(sum, max)
    return when {
        target < 0.0 -> Classification.CORRODED
        target < 1.0 -> Classification.MUNDANE
        target < 5.0 -> Classification.IDEAL
        target < 20.0 -> Classification.ENCHANTED
        else -> Classification.ARTIFACT
    }
}