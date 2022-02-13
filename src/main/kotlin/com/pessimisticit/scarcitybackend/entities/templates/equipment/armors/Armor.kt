package com.pessimisticit.scarcitybackend.entities.templates.equipment.armors

import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.DamageShape
import javax.persistence.*


@Entity
class Armor<T : Armor<T>> : Equipment<T>() {
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
