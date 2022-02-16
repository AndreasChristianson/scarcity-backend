package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.Template
import javax.persistence.*

@Entity
abstract class ModifierTemplate<T :Template> : Template() {
    abstract fun modify(toBeModified: GameObject<T>): GameObject<T>
}
