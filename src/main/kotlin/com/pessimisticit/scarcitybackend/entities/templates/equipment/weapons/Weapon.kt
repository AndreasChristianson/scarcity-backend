package com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons

import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
class Weapon<T : Weapon<T>> : Equipment<T>() {
    @Column(nullable = false)
    var damagePerTurn: Double = 0.0

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var damageShape: DamageShape

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var damageType: DamageType

    @Column(nullable = false)
    var swingDuration: Long = 0

    @Column(nullable = false)
    var readyDuration: Long = 0

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    lateinit var slot: WeaponSlot
}

enum class DamageShape {
    PIERCING,
    SLASHING,
    BLUDGEONING,
    CHOPPING,
    ENVELOPING,
    PROXIMITY,
    CONVECTION,
}

enum class DamageType {
    PHYSICAL, FROST, FIRE,
}