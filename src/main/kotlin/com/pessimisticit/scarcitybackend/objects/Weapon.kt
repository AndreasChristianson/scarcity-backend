package com.pessimisticit.scarcitybackend.objects

import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.objects.procs.WeaponProc

class Weapon(
    var maxRange: Double,
    var additionalDamage: MutableCollection<DamageSpecification>,
    var baseDamage: DamageSpecification,
    var swingTimeout: Long,
    var weaponType: WeaponType,
    var procs: MutableCollection<WeaponProc>,
) : GameObject() {
    private val averageDamagePerSwing: Double
        get() = additionalDamage.fold(baseDamage.distribution.average) { sum, damageSpec -> sum + damageSpec.distribution.average }
    private val swingsPerSecond: Double
        get() = TURN_DURATION / swingTimeout
    val dps: Double
        get() = averageDamagePerSwing * swingsPerSecond
}

