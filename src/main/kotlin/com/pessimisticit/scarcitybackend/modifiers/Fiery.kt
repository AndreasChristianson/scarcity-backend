package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.distributions.FixedDistribution
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import java.net.URI
import javax.persistence.Entity

@Entity
@LootableModifier(
    rarity = Rarity.UNCOMMON,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
open class Fiery : Modifier() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "This weapon deals a small amount of fire damage each swing"
    override val name: String
        get() = "fiery"

    override fun modifyDamageSpecifications(damageSpecifications: Sequence<DamageSpecification>): Sequence<DamageSpecification> {
        return damageSpecifications.plus(
            DamageSpecification(
                DamageType.FIRE,
                DamageShape.PROXIMITY,
                FixedDistribution(5.0)
            )
        )
    }

    override fun modifyPrefix(prefix: String?): String {
        return "fiery"
    }
}
