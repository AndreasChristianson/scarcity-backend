package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee

import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import javax.persistence.Entity

abstract class MeleeWeapon : Weapon() {
    override val baseMinRange: Double
        get() = 0.0
    override val baseMaxDurability: Double
        get() = super.baseMaxDurability * 1.5
}

