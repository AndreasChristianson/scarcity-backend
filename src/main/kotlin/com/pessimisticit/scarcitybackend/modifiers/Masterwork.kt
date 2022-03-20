package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.RARE,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
open class Masterwork : PerfectlyWeighted() {
    override val name: String
        get() = "masterwork"
    override val priority: Double
        get() = 9.0

    override fun modifyDps(dps: Double): Double {
        return dps * 1.2
    }

    override fun modifyDurability(durability: Double): Double {
        return durability * 1.1
    }

    override fun modifyPrefix(prefix: String?): String {
        return "masterwork"
    }
}
