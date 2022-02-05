package com.pessimisticit.scarcitybackend.entities.templates.equipment.armors

import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Armor<T : Armor<T>> : Equipment<T>() {
    @Column
    open var ac: Double = 0.0

    @Enumerated(EnumType.STRING)
    @Column
    open lateinit var slot: ArmorSlot
}
