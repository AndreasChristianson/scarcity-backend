package com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons

import javax.persistence.Entity

@Entity
class RangedWeaponTemplate: WeaponTemplate() {
    open var minRange: Double = 0.0 //meters
}
