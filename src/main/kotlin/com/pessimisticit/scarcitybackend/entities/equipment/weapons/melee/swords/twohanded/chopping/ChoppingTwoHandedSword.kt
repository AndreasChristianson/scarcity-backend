package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.twohanded.chopping

import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.TwoHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import java.net.URI

abstract class ChoppingTwoHandedSword : TwoHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.CHOPPING
    override val iconUri: URI
        get() = URI("$svgRoot/dripping-wound.svg")
}

