package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.constants.ArmorSlot
import com.pessimisticit.scarcitybackend.objects.Armor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated


@Entity
class ArmorTemplate : EquipmentTemplate<Armor>() {
    @Enumerated(EnumType.STRING)
    @Column
    lateinit var slot: ArmorSlot
    override fun generateInstance(): Armor {
        return Armor(

        )
    }
}
