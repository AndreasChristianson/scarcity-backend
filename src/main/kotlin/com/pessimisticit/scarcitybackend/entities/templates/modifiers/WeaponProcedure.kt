package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.mechanics.combat.ProcAction
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcSource
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTarget
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTrigger
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.objects.procs.WeaponProc
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class WeaponProcTemplate : WeaponModifierTemplate() {

    open var procChance: Double = 0.005

    @Column(name = "timeout")
    open var procTimeout: Long = 10000

    @Enumerated(EnumType.STRING)
    open var procTarget: ProcTarget = ProcTarget.TARGET

    @Enumerated(EnumType.STRING)
    open var procSource: ProcSource = ProcSource.SELF

    @Enumerated(EnumType.STRING)
    open var procTrigger: ProcTrigger = ProcTrigger.SUCCESSFUL_ATTACK

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    open lateinit var proc: ProcAction
    override fun modify(toBeModified: Weapon): Weapon {
        return super.modify(toBeModified).also {
            it.procs.add(generateProc())
        }
    }

    open fun generateProc(): WeaponProc {
        return WeaponProc(
            procChance = procChance,
            procTimeout = procTimeout,
            procTarget = procTarget,
            procAction = proc,
            procSource = procSource,
            procTrigger = procTrigger,
        )
    }
}
