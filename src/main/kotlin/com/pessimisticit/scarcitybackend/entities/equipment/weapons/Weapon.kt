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
    protected abstract val baseRange: Double
    protected abstract val baseMinRange: Double
    abstract val weaponType: WeaponType

    private val damagePerSwing
        get() = dps / swingsPerSecond
    private val swingsPerSecond: Double
        get() = TURN_DURATION / swingResetDuration.toDouble()

    protected open val dpsBudgetMultiplier: Double
        get() = 1.0
    open val damageType: DamageType
        get() = DamageType.PHYSICAL
    protected open val baseSwingResetDuration: Long
        get() = 1500
    val swingResetDuration
        get() = applyModifiers(baseSwingResetDuration, Modifier::modifySwingResetDuration)
    private val baseDamage
        get() = sequenceOf(
            DamageSpecification(
                damageType,
                damageShape,
                damagePerSwing
            )
        )
    val damage
        get() = applyModifiers(baseDamage, Modifier::modifyDamageSpecifications)
    val averageDps
        get() = damage.sumOf { it.distribution.average } * swingsPerSecond
    private val baseDps: Double
        get() = itemLevel * dpsBudgetMultiplier
    private val dps
        get() = applyModifiers(baseDps, Modifier::modifyDps)

    override val baseWeight: Double
        get() = 1000.0
    override val baseMaxDurability: Double
        get() = itemLevel
}
