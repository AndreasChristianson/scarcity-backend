package com.pessimisticit.scarcitybackend.objects.procs

import com.pessimisticit.scarcitybackend.formatting.NumberFormatter
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcAction
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcSource
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTarget
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcTrigger
import java.net.URI

open class WeaponProc(
    var procChance: Double,
    var procTimeout: Long,
    var procTarget: ProcTarget,
    var procSource: ProcSource,
    var procTrigger: ProcTrigger,
    var procAction: ProcAction,
) : Displayable {
    override val name: String
        get() = "$procAction: ${NumberFormatter.formatPercent(procChance)}"
    override val description: String
        get() = """
            |${NumberFormatter.formatPercent(procChance)}
            |chance to $procAction $procTarget 
            |upon $procTrigger. Cannot occur more
            |than once per ${NumberFormatter.formatGameDuration(procTimeout)}
            |""".trimMargin()
    override val icon: URI
        get() = procAction.icon
}