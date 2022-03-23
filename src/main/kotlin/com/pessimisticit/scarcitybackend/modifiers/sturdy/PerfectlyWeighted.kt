package com.pessimisticit.scarcitybackend.modifiers.sturdy
import com.pessimisticit.scarcitybackend.constants.Color
import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.UNCOMMON,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
open class PerfectlyWeighted : Heavy() {
    override val icon: SvgIcon
        get() = super.icon.withColor(Color.SILVER.hex)
    override val description: String
        get() = "It feels like an extension of one's arm"
    override val name: String
        get() = "perfectly weighted"

    override fun modifyDps(dps: Double): Double {
        return dps * 1.1
    }

    override fun modifySwingResetDuration(swingResetDuration: Long): Long {
        return (swingResetDuration*0.9).toLong()
    }
}
