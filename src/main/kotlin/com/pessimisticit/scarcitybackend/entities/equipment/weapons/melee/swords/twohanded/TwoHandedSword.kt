package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded


import com.pessimisticit.scarcitybackend.constants.WeaponSlot
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.Sword
import javax.persistence.Entity

@Entity
abstract class TwoHandedSword : Sword() {
    override val slot: WeaponSlot
        get() = WeaponSlot.TWO_HAND
    override val maxRange: Double
        get() = 1.1
    override val baseWeight: Double
        get() = super.baseWeight * 1.7
    override val baseSwingResetDuration: Long
        get() = (super.baseSwingResetDuration * 1.6).toLong()
}

