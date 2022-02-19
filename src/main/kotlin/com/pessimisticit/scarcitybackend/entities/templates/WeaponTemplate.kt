package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.constants.*
import com.pessimisticit.scarcitybackend.distributions.TruncatedGaussianDistribution
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.objects.Weapon
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class WeaponTemplate : EquipmentTemplate<Weapon>() {
    @Enumerated(EnumType.STRING)
    open lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    open lateinit var damageType: DamageType

    open var swingDuration: Long = 0 //ms

    open var readyDuration: Long = 0 //ms

    @Enumerated(EnumType.STRING)
    open lateinit var slot: WeaponSlot

    open var maxRange: Double = 0.0 //meters

    @Column(name = "type")
    open lateinit var weaponType: WeaponType

    open val damagePerSwing
        get() = dps / swingsPerSecond

    val totalSwingTime: Long
        get() = swingDuration + readyDuration
    val swingsPerSecond: Double
        get() = TURN_DURATION / totalSwingTime
    val dps: Double
        get() = baseLevel * rarity.budgetMultiplier

    override fun generateInstance(): Weapon {
        return Weapon(
            weaponType = weaponType,
            readyDuration = readyDuration,
            swingDuration = swingDuration,
            additionalDamage = listOf(),
            baseDamage = DamageSpecification(
                damageType = damageType,
                damageShape = damageShape,
                distribution = TruncatedGaussianDistribution(
                    center = damagePerSwing,
                    stdDev = damagePerSwing * damageShape.relativeSpread
                )
            ),
            maxRange = maxRange,

            ).also {
            it.flavor = flavor
            it.icon = icon
            it.label = label
            it.description = description
        }
    }
}
