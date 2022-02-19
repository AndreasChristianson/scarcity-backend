package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.Entity

@Entity
abstract class ModifierTemplate<T : GameObject> : Template() {
    abstract fun modify(toBeModified: T): T
}
