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
    override val icon: URI
        get() = URI("https://todo.com")
    override val description: String
        get() = """
                |Todo
                """.trimMargin()
    override val name: String
        get() = "long sword"
    override val itemLevel: Double
        get() = 15.0
}

