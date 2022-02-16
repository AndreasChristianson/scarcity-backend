package com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons

import com.pessimisticit.scarcitybackend.combat.TURN_DURATION
import com.pessimisticit.scarcitybackend.entities.templates.equipment.EquipmentTemplate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
open class WeaponTemplate: EquipmentTemplate() {
    open var damagePerTurn: Double = 0.0

    @Enumerated(EnumType.STRING)
    open lateinit var damageShape: DamageShape

    @Enumerated(EnumType.STRING)
    open lateinit var damageType: DamageType

    open var swingDuration: Long = 0 //ms

    open var readyDuration: Long = 0 //ms

    @Enumerated(EnumType.STRING)
    open lateinit var slot: WeaponSlot

    open var maxRange: Double = 0.0 //meters

    open val damagePerSwing
        get() = run {
            val totalTimePerSwing = swingDuration + readyDuration
            val swingsPerTurn = totalTimePerSwing / TURN_DURATION
            damagePerTurn / swingsPerTurn
        }

    @Column(name = "type")
    open lateinit var weaponType: WeaponType
}
