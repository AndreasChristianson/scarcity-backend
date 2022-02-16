package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.constants.BindType
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
abstract class EquipmentTemplate: Template() {
    @Enumerated(EnumType.STRING)
    open lateinit var bindType: BindType

    open var weight: Double = 0.0 //grams
}

