package com.pessimisticit.scarcitybackend.entropy

import net.andrewmao.probability.TruncatedNormal
import kotlin.random.Random

object Roller {
    fun truncatedGaussian(
        center: Double = 0.0,
        stdev: Double = 1.0,
        min: Double = Double.NEGATIVE_INFINITY,
        max: Double = Double.POSITIVE_INFINITY,
    ): Double {
        require(max > min) {
            "Max must be greater than min"
        }
        return TruncatedNormal(
            mean = center,
            ub = max,
            lb = min,
            sd = stdev
        ).sample()
    }

    fun truncatedGaussianInt(
        center: Double = 0.0,
        stdev: Double = 1.0,
        min: Int = Int.MIN_VALUE,
        max: Int = Int.MAX_VALUE,
    ): Int = truncatedGaussian(
        center = center,
        max = max.toDouble(),
        min = min.toDouble(),
        stdev = stdev,
    ).toInt()

    fun <T> select(
        items: Sequence<ItemGenerator<T>>,
    ): ItemGenerator<T> = select(items) { Random.nextDouble(it) }

    fun <T> select(
        items: Sequence<ItemGenerator<T>>,
        rollFunction: (Double) -> Double
    ): ItemGenerator<T> {
        val total = items
            .map { it.rarity.relativeWeight }
            .fold(0.0) { accumulator, element ->
                accumulator + element
            }
        val target = rollFunction(total)
        var accumulator = 0.0
        return items
            .dropWhile {
                accumulator += it.rarity.relativeWeight
                accumulator <= target
            }
            .first()
    }
}
