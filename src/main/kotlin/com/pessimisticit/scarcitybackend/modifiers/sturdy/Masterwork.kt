package com.pessimisticit.scarcitybackend.modifiers.sturdy

import com.pessimisticit.scarcitybackend.constants.Color
import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.images.SvgIcon
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.RARE,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
open class Masterwork : PerfectlyWeighted() {
    override val icon: SvgIcon
        get() = super.icon.withColor(Color.GOLD.hex)
    override val name: String
        get() = "masterwork"

    override fun modifyDps(dps: Double): Double {
        return dps * 1.2
    }

    override fun modifyDurability(durability: Double): Double {
        return durability * 1.1
    }
}
