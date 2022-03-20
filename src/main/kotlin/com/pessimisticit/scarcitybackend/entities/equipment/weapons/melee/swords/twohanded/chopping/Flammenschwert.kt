package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.chopping

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entropy.Lootable
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.UNCOMMON,
    level = 35.0,
)
class Flammenschwert : Doppelhander() {
    override val icon: URI
        get() = URI("https://todo.com")
    override val description: String
        get() = """
                |A flame-bladed sword has a characteristically undulating style of blade. The design of the blade is
                |decorative along with being functional by causing unpleasant vibrations while parrying
                """.trimMargin()
    override val name: String
        get() = "Flammenschwert"
    override val itemLevel: Double
        get() = 35.0
    override val baseRange: Double
        get() = 1.35
    override val baseWeight: Double
        get() = 3200.0
}

