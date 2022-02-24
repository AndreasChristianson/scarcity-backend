package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.constants.WeaponSlot
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.distributions.TruncatedGaussianDistribution
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
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

    @Column(name = "timeout")
    open var swingTimeout: Long = 0 //ms

    @Enumerated(EnumType.STRING)
    open lateinit var slot: WeaponSlot

    open var maxRange: Double = 0.0 //meters

    @Column(name = "type")
    open lateinit var weaponType: WeaponType

    open val damagePerSwing
        get() = dps / swingsPerSecond

    val swingsPerSecond: Double
        get() = TURN_DURATION / swingTimeout
    val dps: Double
        get() = baseLevel * rarity.budgetMultiplier

    override fun generateInstance(): Weapon {
        return Weapon(
            weaponType = weaponType,
            swingTimeout = swingTimeout,
            additionalDamage = mutableListOf(),
            baseDamage = DamageSpecification(
                damageType = damageType,
                damageShape = damageShape,
                center = damagePerSwing
            ),
            maxRange = maxRange,
            procs = mutableListOf(),
        ).also {
            it.flavor = flavor
            it.icon = icon
            it.label = label
            it.description = description
        }
    }
}
