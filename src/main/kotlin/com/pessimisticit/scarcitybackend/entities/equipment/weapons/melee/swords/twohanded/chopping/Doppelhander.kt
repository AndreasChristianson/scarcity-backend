package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.chopping

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entropy.Lootable
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.COMMON,
    level = 30.0,
)
open class Doppelhander : ChoppingTwoHandedSword() {
    override val description: String
        get() = """
                |These swords represent the final stage in the trend of increasing size that started in the 14th century.
                |They acquired the characteristics of a polearm rather than a sword due to their large size and weight 
                |and therefore increased range and striking power.
                """.trimMargin()
    override val baseName: String
        get() = "doppelhänder"
    override val itemLevel: Double
        get() = 30.0
    override val baseRange: Double
        get() = 1.35
    override val baseWeight: Double
        get() = 3200.0
}

