package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import com.pessimisticit.scarcitybackend.objects.procs.DamageProc
import com.pessimisticit.scarcitybackend.objects.procs.WeaponProc
import javax.persistence.Entity

@Entity
class DamageProcTemplate : WeaponProcTemplate() {
    lateinit var damageType: DamageType
    lateinit var damageShape: DamageShape
    var damage: Double = 0.0
    override fun generateProc(): WeaponProc {
        return DamageProc(
            procChance = procChance,
            procTimeout = procTimeout,
            procTarget = procTarget,
            procAction = proc,
            procSource = procSource,
            procTrigger = procTrigger,
            damage = damage,
            damageShape = damageShape,
            damageType = damageType,
        )
    }
}
