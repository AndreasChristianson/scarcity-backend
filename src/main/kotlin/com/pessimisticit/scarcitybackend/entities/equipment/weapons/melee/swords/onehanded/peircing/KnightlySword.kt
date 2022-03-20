package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entropy.Lootable
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.COMMON,
    level = 15.0,
)
class KnightlySword : PiercingOneHandedSword() {
    override val icon: URI
        get() = URI("https://todo.com")
    override val description: String
        get() = """
                |A straight, double-edged weapon with a single-handed, cruciform hilt
                """.trimMargin()
    override val name: String
        get() = "knightly sword"
    override val itemLevel: Double
        get() = 15.0
    override val flavor: String
        get() = "+NDXOXCHWDRGHDXORVI+"
    override val baseRange: Double
        get() = 0.75
}

