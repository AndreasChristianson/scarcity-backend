package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.abilities.evocations.EvokeLight
import com.pessimisticit.scarcitybackend.distributions.FixedDistribution
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import java.net.URI
import javax.persistence.Entity

@Entity
class Dazzling : Fiery() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "This weapon shines brightly, searing those it damages"
    override val name: String
        get() = "dazzling"

    override fun modifyDamageSpecifications(damageSpecifications: Sequence<DamageSpecification>): Sequence<DamageSpecification> {
        return damageSpecifications.plus(
            DamageSpecification(
                DamageType.FIRE,
                DamageShape.PROXIMITY,
                FixedDistribution(5.0)
            )
        )
    }

    override fun modifyAbilities(abilities: Sequence<Ability>): Sequence<Ability> {
        return abilities.plus(
            EvokeLight
        )
    }

    override fun modifyPrefix(prefix: String?): String {
        return "dazzling"
    }
}
