package com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons

import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import org.springframework.hateoas.server.core.Relation
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class RangedWeapon<T:RangedWeapon<T>> : Weapon<T>() {
    var minRange: Double = 0.0 //meters
}
