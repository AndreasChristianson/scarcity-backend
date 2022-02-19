package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.constants.DamageShape
import com.pessimisticit.scarcitybackend.constants.DamageType
import com.pessimisticit.scarcitybackend.distributions.TruncatedGaussianDistribution
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.objects.Weapon
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class BonusDamage : WeaponModifierTemplate() {
    @Enumerated(EnumType.STRING)
    open lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    open lateinit var damageType: DamageType

    open var doubleValue: Double = 5.0

    open var stringValue: String? = null

    override fun modify(toBeModified: Weapon): Weapon {
        return toBeModified.also {
            it.additionalDamage = toBeModified.additionalDamage.plus(
                DamageSpecification(
                    damageType = damageType,
                    damageShape = damageShape,
                    distribution = TruncatedGaussianDistribution(
                        center = doubleValue,
                        stdDev = doubleValue * damageShape.relativeSpread
                    )
                )
            )
            it.suffix = stringValue ?: it.prefix
        }
    }
}