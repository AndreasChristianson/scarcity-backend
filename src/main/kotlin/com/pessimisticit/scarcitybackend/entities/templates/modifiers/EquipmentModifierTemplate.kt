package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.constants.BindType
import com.pessimisticit.scarcitybackend.objects.Equipment
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class EquipmentModifierTemplate<T : Equipment> : ModifierTemplate<T>() {
    @Enumerated(EnumType.STRING)
    open var minBindType: BindType? = null

    override fun modify(toBeModified: T): T {
        return super.modify(toBeModified).also {
            it.bindType = it.bindType.max(minBindType)
        }
    }
}
