package com.pessimisticit.scarcitybackend.entities.equipment.weapons

import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.constants.WeaponSlot
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import javax.persistence.Entity

@Entity
abstract class Weapon : Equipment() {
    abstract val damageShape: DamageShape
    abstract val slot: WeaponSlot
    abstract val maxRange: Double
    abstract val minRange: Double
    abstract val weaponType: WeaponType

    val damagePerSwing
        get() = dps / swingsPerSecond
    val swingsPerSecond
        get() = TURN_DURATION / swingResetDuration

    open val dpsBudgetMultiplier: Double
        get() = 1.0
    open val damageType: DamageType
        get() = DamageType.PHYSICAL
    open val baseSwingResetDuration: Long
        get() = 1500
    val swingResetDuration
        get() = applyModifiers(baseSwingResetDuration, Modifier::modifySwingResetDuration)
    val baseDamage
        get() = sequenceOf(
            DamageSpecification(
                damageType,
                damageShape,
                damagePerSwing
            )
        )
    val damage
        get() = applyModifiers(baseDamage, Modifier::modifyDamageSpecifications)
    val baseDps: Double
        get() = itemLevel * dpsBudgetMultiplier
    val dps
        get() = applyModifiers(baseDps, Modifier::modifyDps)

    override val baseWeight: Double
        get() = 1000.0
    override val baseMaxDurability: Double
        get() = itemLevel
}
