package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entropy.Lootable
import com.pessimisticit.scarcitybackend.modifiers.Dazzling
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.EXTRAORDINARY,
    level = 15.0
)
class DazzlingLongSword : LongSword() {
    override val icon: URI
        get() = URI("https://todo.com")
    override val description: String
        get() = """
                        |Todo
                    """.trimMargin()
    override val baseModifiers
        get() = sequenceOf(Dazzling())

    override val itemLevel: Double
        get() = 15.0
}
