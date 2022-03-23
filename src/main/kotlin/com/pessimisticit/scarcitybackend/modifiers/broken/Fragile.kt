package com.pessimisticit.scarcitybackend.modifiers.broken

import com.pessimisticit.scarcitybackend.constants.Color
import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.COMMON,
    modifierType = ModifierType.HARMFUL,
    modifierTargets = [Equipment::class]
)
open class Fragile : Modifier() {
    override val icon
        get() = SvgIcon(URI("$svgRoot/cracked-glass.svg"),Color.BRONZE.hex)
    override val description: String
        get() = "Will break at any moment"
    override val name: String
        get() = "fragile"
    override val priority: Double
        get() = 0.0

    override fun modifyDurability(durability: Double): Double {
        return durability * 0.7
    }
}
