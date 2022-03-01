package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.objects.Weapon
import javax.persistence.Entity

@Entity
class ScaleWeaponModifierTemplate:WeaponModifierTemplate() {

    var damageMultiplier: Double = 1.0
    var weightMultiplier: Double = 1.0
    var swingTimeMultiplier: Double = 1.0

    override fun modify(toBeModified: Weapon): Weapon {
        return super.modify(toBeModified).also {
            it.swingTimeout = (it.swingTimeout * swingTimeMultiplier).toLong()
            it.baseDamage.distribution.scale(damageMultiplier)
            it.weight*=weightMultiplier
        }
    }
}
