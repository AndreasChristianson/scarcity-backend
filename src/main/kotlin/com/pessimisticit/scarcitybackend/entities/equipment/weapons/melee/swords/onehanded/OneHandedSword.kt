package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.onehanded


import com.pessimisticit.scarcitybackend.constants.WeaponSlot
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords.Sword
import javax.persistence.Entity

@Entity
abstract class OneHandedSword : Sword() {
    override val slot: WeaponSlot
        get() = WeaponSlot.ONE_HAND
    override val baseRange: Double
        get() = 0.6
    override val baseWeight: Double
        get() = super.baseWeight * 0.9
    override val baseSwingResetDuration: Long
        get() = (super.baseSwingResetDuration * 0.8).toLong()
}

