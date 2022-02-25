package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.entities.templates.HasRelativeRarity
import net.andrewmao.probability.TruncatedNormal

interface Roller {
    fun <T : HasRelativeRarity> select(items: Collection<T>): T
    fun getModifierCount(): Int

    companion object {
        fun truncatedGaussian(
            center: Double,
            stdev: Double,
            min: Double,
            max: Double,
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
        ): Int {
            return truncatedGaussian(
                center = center,
                max = max.toDouble(),
                min = min.toDouble(),
                stdev = stdev,
            ).toInt()
        }

        fun <T : HasRelativeRarity> select(
            items: Collection<T>,
            rollFunction: (Int) -> Int
        ): T {
            val total = items.fold(0) { accumulator, element ->
                accumulator + element.rarity.relativeWeight
            }
            val target = rollFunction(total)
            var accumulator = 0
            return items.dropWhile {
                accumulator += it.rarity.relativeWeight
                accumulator <= target
            }.first()
        }
    }

}