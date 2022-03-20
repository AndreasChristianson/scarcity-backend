package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.COMMON,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Equipment::class]
)
open class Heavy : Modifier() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "It is made of heavier material than one expects"
    override val name: String
        get() = "heavy"
    override val priority: Double
        get() = 3.0

    override fun modifyDps(dps: Double): Double {
        return dps * 1.1
    }

    override fun modifyWeight(weight: Double): Double {
        return weight * 1.2
    }

    override fun modifyDurability(durability: Double): Double {
        return durability * 1.2
    }

    override fun modifyPrefix(prefix: String?): String {
        return "heavy"
    }
}
