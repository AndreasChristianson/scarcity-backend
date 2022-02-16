package com.pessimisticit.scarcitybackend.entities.templates

import javax.persistence.Entity

@Entity
class RangedWeaponTemplate: WeaponTemplate() {
    open var minRange: Double = 0.0 //meters
}
