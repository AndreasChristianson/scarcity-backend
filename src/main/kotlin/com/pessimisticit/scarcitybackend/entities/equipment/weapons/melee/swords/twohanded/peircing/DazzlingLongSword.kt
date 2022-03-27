package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entropy.Lootable
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.PngIcon
import com.pessimisticit.scarcitybackend.images.Position
import com.pessimisticit.scarcitybackend.modifiers.fiery.Dazzling
import java.net.URI
import javax.persistence.Entity

@Entity
@Lootable(
    rarity = Rarity.EXTRAORDINARY,
    level = 15.0,
    tags = [Tag.UNRAND]
)
class DazzlingLongSword : LongSword() {
    override val icon: GameIcon
        get() = PngIcon(URI("/static/png/weapons-1.png"), Position(18 * 32, 7 * 32))
    override val description: String
        get() = """
                |It shines and sears like the sun.
                """.trimMargin()
    override val baseModifiers
        get() = sequenceOf(Dazzling())

    override val itemLevel: Double
        get() = 15.0
    override val flavor: String?
        get() = "There is in God, some say, a deep but dazzling darkness."

    override fun acceptModifier(modifier: Modifier) = false
}
