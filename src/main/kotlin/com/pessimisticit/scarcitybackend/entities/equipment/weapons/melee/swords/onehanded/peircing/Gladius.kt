package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entropy.Lootable
import javax.persistence.Entity

@Entity
@Lootable(
    tags = [Tag.ROMAN, Tag.ANCIENT],
    rarity = Rarity.UNCOMMON,
    level = 10.0,
)
class Gladius : PiercingOneHandedSword() {
    override val description: String
        get() = """
                        |A two-edged sword with a tapered point for stabbing during thrusting.
                        |A solid grip is provided by a knobbed hilt with ridges for the fingers.
                        |The previous owner's name is engraved on the blade.
                    """.trimMargin()
    override val name: String
        get() = "gladius"
    override val itemLevel: Double
        get() = 10.0
    override val flavor: String
        get() = "Quemadmodum gladius neminem occidit, occidentis telum est."
    override val baseSwingResetDuration: Long
        get() = (super.baseSwingResetDuration * 0.9).toLong()
}

