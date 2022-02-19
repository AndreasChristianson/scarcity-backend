package com.pessimisticit.scarcitybackend.objects

import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification

class Weapon(
    var maxRange: Double,
    var additionalDamage: Collection<DamageSpecification>,
    var baseDamage: DamageSpecification,
    var swingDuration: Long,
    var readyDuration: Long,
    var weaponType: WeaponType,
) : GameObject() {
    val averageDamagePerSwing: Double
        get() = additionalDamage.fold(baseDamage.distribution.average) { sum, damageSpec -> sum + damageSpec.distribution.average }
    val totalSwingTime: Long
        get() = swingDuration + readyDuration
    val swingsPerSecond: Double
        get() = TURN_DURATION / totalSwingTime
    val dps: Double
        get() = averageDamagePerSwing * swingsPerSecond
}