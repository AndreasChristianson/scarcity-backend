package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing.PiercingTwoHandedSword
import com.pessimisticit.scarcitybackend.entropy.Lootable
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.COMMON,
    level = 20.0,
)
class Estoc : PiercingTwoHandedSword() {
    override val description: String
        get() = """
                |a straight, edgeless, but sharply pointed blade.
                |It is noted for its ability to pierce mail armor.
                """.trimMargin()
    override val name: String
        get() = "Estoc"
    override val itemLevel: Double
        get() = 20.0
    override val flavor: String
        get() = "Not to be confused with a modern bullfighting espada de matar toros."
    override val baseRange: Double
        get() = 1.07
    override val baseWeight: Double
        get() = 2400.0
}

