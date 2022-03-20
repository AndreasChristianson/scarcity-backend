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
    modifierType = ModifierType.HARMFUL,
    modifierTargets = [Equipment::class]
)
open class Flimsy : Modifier() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "It feels light and fragile"
    override val name: String
        get() = "flimsy"
    override val priority: Double
        get() = 0.0

    override fun modifyDps(dps: Double): Double {
        return dps * 0.9
    }

    override fun modifyWeight(weight: Double): Double {
        return weight * 0.9
    }

    override fun modifyDurability(durability: Double): Double {
        return durability * 0.8
    }

    override fun modifyPrefix(prefix: String?): String {
        return "flimsy"
    }
}
