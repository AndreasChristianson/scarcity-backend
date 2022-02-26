package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.objects.Weapon
import javax.persistence.Entity

@Entity
abstract class WeaponModifierTemplate : EquipmentModifierTemplate<Weapon>()
