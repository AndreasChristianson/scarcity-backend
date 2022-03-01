package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.entities.templates.HasRelativeRarity
import kotlin.random.Random

object CommonRoller : Roller {
    override fun <T : HasRelativeRarity> select(items: Collection<T>): T {
        return Roller.select(
            items
        ) { total: Int -> Random.nextInt(total) }
    }

    override fun rollBeneficialModifierCount(): Int {
        return Roller.truncatedGaussianInt(center = -.5)
    }

    override fun rollNeutralModifierCount(): Int {
        return Roller.truncatedGaussianInt(center = -0.5)
    }

    override fun rollHarmfulModifierCount(): Int {
        return Roller.truncatedGaussianInt(center = -1.0)
    }
}
