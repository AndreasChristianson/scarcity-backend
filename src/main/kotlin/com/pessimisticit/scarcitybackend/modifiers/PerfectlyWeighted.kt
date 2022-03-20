package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.UNCOMMON,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
open class PerfectlyWeighted : Modifier() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "It feels like an extension of one's arm"
    override val name: String
        get() = "perfectly weighted"
    override val priority: Double
        get() = 5.0

    override fun modifyDps(dps: Double): Double {
        return dps * 1.1
    }

    override fun modifySwingResetDuration(swingResetDuration: Long): Long {
        return (swingResetDuration*0.9).toLong()
    }

    override fun modifyPrefix(prefix: String?): String {
        return "perfectly weighted"
    }
}
