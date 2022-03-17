package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.OneHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape

abstract class PiercingOneHandedSword : OneHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.PIERCING
}

