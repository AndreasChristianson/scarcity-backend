package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing.PiercingOneHandedSword
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing.PiercingTwoHandedSword
import com.pessimisticit.scarcitybackend.entropy.Lootable
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.COMMON,
    level = 25.0,
)
class Claymore : PiercingTwoHandedSword() {
    override val icon: URI
        get() = URI("https://todo.com")
    override val description: String
        get() = """
                |A Scottish two-handed sword with a cross hilt of forward-sloping quillons with quatrefoil terminations.
                """.trimMargin()
    override val name: String
        get() = "Claymore"
    override val itemLevel: Double
        get() = 25.0
    override val flavor: String
        get() = "claidheamh mór"
    override val baseRange: Double
        get() = 1.15
    override val baseWeight: Double
        get() = 2700.0
}

