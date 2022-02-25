package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.entities.templates.HasRelativeRarity

object EliteRoller : Roller {
    override fun <T : HasRelativeRarity> select(items: Collection<T>): T {
        return Roller.select(
            items.sortedBy { it.rarity }
        ) { total: Int ->
            Roller.truncatedGaussianInt(
                center = total * 0.9,
                stdev = total / 3.0,
                min = 0,
                max = total,
            )
        }
    }

    override fun getModifierCount(): Int {
        return Roller.truncatedGaussianInt(
            center = 2.0,
            min = 1,
            stdev = 0.7
        )
    }
}
