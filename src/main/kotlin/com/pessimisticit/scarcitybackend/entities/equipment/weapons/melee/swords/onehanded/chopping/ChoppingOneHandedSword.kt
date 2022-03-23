package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.peircing

import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded.OneHandedSword
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import java.net.URI
import javax.persistence.Entity

abstract class ChoppingOneHandedSword : OneHandedSword() {
    override val damageShape: DamageShape
        get() = DamageShape.CHOPPING
    override val iconUri: URI
        get() = URI("$svgRoot/croc-sword.svg")
}

