package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.TwoHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape

abstract class PiercingTwoHandedSword : TwoHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.PIERCING
}

