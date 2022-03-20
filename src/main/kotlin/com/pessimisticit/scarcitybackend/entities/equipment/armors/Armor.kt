package com.pessimisticit.scarcitybackend.entities.equipment.armors

import com.pessimisticit.scarcitybackend.constants.ArmorSlot
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import java.util.Dictionary
import javax.persistence.Entity


@Entity
abstract class Armor : Equipment() {
    abstract val slot: ArmorSlot

    abstract val mitigation: Map<DamageType, Double>
}
