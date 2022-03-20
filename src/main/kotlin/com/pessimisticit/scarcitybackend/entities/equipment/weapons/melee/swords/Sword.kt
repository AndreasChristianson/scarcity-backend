package com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.swords


import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.MeleeWeapon
import javax.persistence.Entity

@Entity
abstract class Sword : MeleeWeapon() {
    override val weaponType: WeaponType
        get() = WeaponType.SWORD
}

