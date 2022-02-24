package com.pessimisticit.scarcitybackend.mechanics.combat

import com.pessimisticit.scarcitybackend.formatting.EnumFormatter.toDisplayString
import java.net.URI

enum class ProcAction(val icon: URI) {
    DESTROY_EQUIPMENT(URI("http://example.com")),
    DEAL_DAMAGE(URI("http://example.com")),;

    override fun toString(): String {
        return toDisplayString(this)
    }
}


enum class ProcTarget { // the target of the proc
    SELF,
    TARGET,
    RANDOM_UNIT_IN_LOS,
    RANDOM_ALLY_IN_LOS,
    RANDOM_ENEMY_IN_LOS,;
    override fun toString(): String {
        return toDisplayString(this)
    }
}
enum class ProcSource { // the trigger of the proc
    SELF,
    TARGET,
    ANY_IN_LOS,
    ANY_ALLY_IN_LOS,
    ANY_ENEMY_IN_LOS,;
    override fun toString(): String {
        return toDisplayString(this)
    }
}
enum class ProcTrigger {
    ATTEMPTED_ATTACK,
    SUCCESSFUL_ATTACK,
    UNSUCCESSFUL_ATTACK,
    DAMAGE_TAKEN,
    DAMAGE_PREVENTED,;
    override fun toString(): String {
        return toDisplayString(this)
    }
}