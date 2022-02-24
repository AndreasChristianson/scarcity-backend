package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.distributions.FixedDistribution
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import com.pessimisticit.scarcitybackend.objects.Weapon
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class BonusDamageTemplate : WeaponModifierTemplate() {
    @Enumerated(EnumType.STRING)
    open lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    open lateinit var damageType: DamageType

    open var damage: Double = 5.0

    override fun modify(toBeModified: Weapon): Weapon {
        return super.modify(toBeModified).also {
            it.additionalDamage.add(
                DamageSpecification(
                    damageType = damageType,
                    damageShape = damageShape,
                    distribution = FixedDistribution(
                        fixed = damage
                    )
                )
            )
        }
    }
}