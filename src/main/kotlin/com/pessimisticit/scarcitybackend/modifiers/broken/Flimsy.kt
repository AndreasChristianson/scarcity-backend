package com.pessimisticit.scarcitybackend.modifiers.broken

import com.pessimisticit.scarcitybackend.constants.Color
import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.images.GameIcon
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.COMMON,
    modifierType = ModifierType.HARMFUL,
    modifierTargets = [Equipment::class]
)
open class Flimsy : Broken() {
    override val icon
        get() = super.icon.withColor(Color.SILVER.hex)
    override val description: String
        get() = "It feels light and weak"
    override val name: String
        get() = "flimsy"

    override fun modifyDps(dps: Double): Double {
        return dps * 0.9
    }

    override fun modifyWeight(weight: Double): Double {
        return weight * 0.9
    }
}
