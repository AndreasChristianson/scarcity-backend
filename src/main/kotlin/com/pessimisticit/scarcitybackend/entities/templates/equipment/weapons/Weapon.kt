package com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons

import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import org.springframework.hateoas.server.core.Relation
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class Weapon<T:Weapon<T>> : Equipment<T>() {
    var damagePerTurn: Double = 0.0

    @Enumerated(EnumType.STRING)
    lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    lateinit var damageType: DamageType

    var swingDuration: Long = 0 //ms

    var readyDuration: Long = 0 //ms

    @Enumerated(EnumType.STRING)
    lateinit var slot: WeaponSlot

    var maxRange: Double = 0.0 //meters

    open val damagePerSwing
        get() = run {
            val totalTimePerSwing = swingDuration + readyDuration
            val swingsPerTurn = totalTimePerSwing / TURN_DURATION
            damagePerTurn / swingsPerTurn
        }

    @Column(name = "type")
    lateinit var weaponType: WeaponType
}
