package com.pessimisticit.scarcitybackend.entities.equipment

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Modifier
import javax.persistence.Entity

@Entity
abstract class Equipment : GameEntity() {
    val weight: Double // in grams
        get() = applyModifiers(baseWeight, Modifier::modifyWeight)
    abstract val baseWeight: Double
    val maxDurability: Double
        get() = applyModifiers(baseMaxDurability, Modifier::modifyDurability)
    abstract val baseMaxDurability: Double
    abstract val itemLevel:Double
}

