package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.constants.ArmorSlot
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated


@Entity
class ArmorTemplate : EquipmentTemplate() {
    @Enumerated(EnumType.STRING)
    @Column
    lateinit var slot: ArmorSlot
}
