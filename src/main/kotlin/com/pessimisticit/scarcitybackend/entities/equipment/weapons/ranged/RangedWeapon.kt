package com.pessimisticit.scarcitybackend.entities.equipment.weapons.ranged

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import javax.persistence.Entity

abstract class RangedWeapon : Weapon() {
    override val baseMinRange: Double
        get() = 5.0 //meters
}

