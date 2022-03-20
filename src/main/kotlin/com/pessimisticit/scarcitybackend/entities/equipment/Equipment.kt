package com.pessimisticit.scarcitybackend.entities.equipment

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.Modifier
import javax.persistence.Entity

@Entity
abstract class Equipment : GameObject() {
    val weight: Double // in grams
        get() = applyModifiers(baseWeight, Modifier::modifyWeight)
    protected abstract val baseWeight: Double
    val maxDurability: Double
        get() = applyModifiers(baseMaxDurability, Modifier::modifyDurability)
    protected abstract val baseMaxDurability: Double
    abstract val itemLevel:Double
}

