package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.chopping

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.TwoHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape

abstract class ChoppingTwoHandedSword : TwoHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.CHOPPING
}

