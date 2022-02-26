package com.pessimisticit.scarcitybackend.objects

import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.objects.procs.DamageProc
import com.pessimisticit.scarcitybackend.objects.procs.WeaponProc

private val WeaponProc.averageDamage: Double
    get() = if (this is DamageProc) {
        this.damageSpecification.distribution.average * this.procChance
    } else {
        0.0
    }

class Weapon(
    var maxRange: Double,
    var baseDamage: DamageSpecification,
    var swingTimeout: Long,
    var weaponType: WeaponType,
    var procs: MutableCollection<WeaponProc>,
) : Equipment() {
    val averageDamagePerSwing: Double
        get() = procs.fold(baseDamage.distribution.average) { sum, proc -> sum + proc.averageDamage }
    val swingsPerSecond: Double
        get() = TURN_DURATION / swingTimeout
    val dps: Double
        get() = averageDamagePerSwing * swingsPerSecond
}

