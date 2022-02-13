package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.Template
import javax.persistence.*

@Entity
abstract class ModifierTemplate<V : Template<V>, T : ModifierTemplate<V, T>> : Template<T>() {
    abstract fun modify(toBeModified: GameObject<V>): GameObject<V>
}
