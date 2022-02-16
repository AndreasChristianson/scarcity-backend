package com.pessimisticit.scarcitybackend.entities.templates.equipment.armors

import com.pessimisticit.scarcitybackend.entities.templates.equipment.EquipmentTemplate
import javax.persistence.*


@Entity
class ArmorTemplate : EquipmentTemplate() {
//    @Column
//    var ac: Double = 0.0

    @Enumerated(EnumType.STRING)
    @Column
    lateinit var slot: ArmorSlot

//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "armor_mitigation")
//    @MapKeyColumn(name = "damage_shape")
//    @MapKeyClass(DamageShape::class)
//    @MapKeyEnumerated(EnumType.STRING)
//    @Column
//    lateinit var acByDamageType: Map<DamageShape, Double>
}
