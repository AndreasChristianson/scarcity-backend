package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.entities.Modifier
import kotlin.random.Random

private fun weighted(weight: Double): (Double) -> Double {
    return {
        Roller.truncatedGaussian(
            center = weight * it,
            min = 0.0,
            max = it,
            stdev = it * 0.3
        )
    }
}

private val uniform: (Double) -> Double
    get() = {
        Random.nextDouble(it)
    }

enum class GenerationProfile(
    val entropySource: (Double) -> Double,
    val modifierCountSource: () -> Int,
    val modifierFilter: (Table<Modifier>) -> Table<Modifier>,
) {
    ELITE(
        weighted(0.7),
        { Roller.truncatedGaussianInt(min = 1) },
        { it.filterByItemLevel(min = 0.0) },
    ),
    COMMON(
        uniform,
        { Roller.truncatedGaussianInt().coerceAtLeast(0) },
        { it },
    ),
    DEGRADED(
        weighted(0.3),
        { Roller.truncatedGaussianInt(min = 1) },
        { it.filterByItemLevel(max = 0.0) },
    ),
    ;
}