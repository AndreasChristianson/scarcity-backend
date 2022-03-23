package com.pessimisticit.scarcitybackend.mechanics.combat

import com.pessimisticit.scarcitybackend.formatting.EnumFormatter.toDisplayString
import java.net.URI

enum class ProcAction{
    DESTROY_EQUIPMENT,
    DEAL_DAMAGE, ;
}


enum class ProcTarget { // the target of the proc
    SELF,
    TARGET,
    RANDOM_UNIT_IN_LOS,
    RANDOM_ALLY_IN_LOS,
    RANDOM_ENEMY_IN_LOS, ;

}

enum class ProcSource { // the trigger of the proc
    SELF,
    TARGET,
    ANY_IN_LOS,
    ANY_ALLY_IN_LOS,
    ANY_ENEMY_IN_LOS, ;

}

enum class ProcTrigger {
    ATTEMPTED_ATTACK,
    SUCCESSFUL_ATTACK,
    UNSUCCESSFUL_ATTACK,
    DAMAGE_TAKEN,
    DAMAGE_PREVENTED, ;

}