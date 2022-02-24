package com.pessimisticit.scarcitybackend.objects.procs

import com.pessimisticit.scarcitybackend.formatting.NumberFormatter
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcAction
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcSource
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTarget
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTrigger
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import java.net.URI

class DamageProc(
    procChance: Double,
    procTimeout: Long,
    procTarget: ProcTarget,
    procSource: ProcSource,
    procTrigger: ProcTrigger,
    procAction: ProcAction,
    damageType: DamageType,
    damageShape: DamageShape,
    damage: Double,
) : WeaponProc(
    procChance,
    procTimeout,
    procTarget,
    procSource,
    procTrigger,
    procAction
) {
    val damageSpecification: DamageSpecification = DamageSpecification(
        damageType = damageType,
        damageShape = damageShape,
        center = damage
    )
    override val name: String
        get() = "${damageSpecification.name}: ${NumberFormatter.formatPercent(procChance)}"
    override val description: String
        get() = """
            | ${NumberFormatter.formatPercent(procChance)}
            | chance to deal ${damageSpecification.name} to $procTarget 
            | upon $procTrigger. Cannot occur more
            | than once per ${NumberFormatter.formatGameDuration(procTimeout)}
            |""".trimMargin()
    override val icon: URI
        get() = damageSpecification.damageType.icon
}