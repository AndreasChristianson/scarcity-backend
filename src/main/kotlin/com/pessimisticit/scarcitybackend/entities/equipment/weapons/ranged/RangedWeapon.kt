package com.pessimisticit.scarcitybackend.entities.equipment.weapons.ranged

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.Table
import javax.persistence.Entity

@Entity
abstract class RangedWeapon : Weapon() {
    override val minRange: Double
        get() = 0.0 //meters
}

