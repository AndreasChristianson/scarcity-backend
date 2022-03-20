package com.pessimisticit.scarcitybackend.entities.equipment.weapons.ranged

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.Table
import javax.persistence.Entity

@Entity
abstract class RangedWeapon : Weapon() {
    override val baseMinRange: Double
        get() = 5.0 //meters
}

