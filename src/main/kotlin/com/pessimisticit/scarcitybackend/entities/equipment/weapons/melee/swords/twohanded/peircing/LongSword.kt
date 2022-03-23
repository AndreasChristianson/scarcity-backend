package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entropy.Lootable
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.UNCOMMON,
    level = 15.0
)
open class LongSword : PiercingTwoHandedSword() {
    override val description: String
        get() = """
                |A sword with a cruciform hilt with a grip for two-handed use.
                |The blade is strongly tapered with a flattened diamond cross-section.
                """.trimMargin()
    override val name: String
        get() = "long sword"
    override val itemLevel: Double
        get() = 15.0
}

