package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.mechanics.combat.ProcAction
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcSource
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTarget
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTrigger
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.objects.procs.DamageProc
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class BonusDamageTemplate : WeaponModifierTemplate() {
    @Enumerated(EnumType.STRING)
    open lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    open lateinit var damageType: DamageType

    open var damage: Double = 5.0

    override fun modify(toBeModified: Weapon): Weapon {
        return super.modify(toBeModified).also {
            it.procs.add(
                DamageProc(
                    damageShape = damageShape,
                    damageType = damageType,
                    damage = damage,
                    procTrigger = ProcTrigger.SUCCESSFUL_ATTACK,
                    procChance = 1.0,
                    procSource = ProcSource.SELF,
                    procAction = ProcAction.DEAL_DAMAGE,
                    procTarget = ProcTarget.TARGET,
                    procTimeout = 0
                )
            )
        }
    }
}