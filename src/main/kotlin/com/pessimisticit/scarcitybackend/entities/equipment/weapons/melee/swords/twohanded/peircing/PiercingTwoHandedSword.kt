package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.TwoHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import java.net.URI

abstract class PiercingTwoHandedSword : TwoHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.PIERCING
    override val iconUri: URI
        get() = URI("$svgRoot/bloody-wound.svg")
}

