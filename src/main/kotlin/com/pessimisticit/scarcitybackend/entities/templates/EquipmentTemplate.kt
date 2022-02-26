package com.pessimisticit.scarcitybackend.entities.templates

import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.Entity

@Entity
abstract class EquipmentTemplate<T : GameObject> : GameObjectTemplate<T>() {
    open var weight: Double = 0.0 //grams
}

