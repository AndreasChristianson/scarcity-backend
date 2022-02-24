package com.pessimisticit.scarcitybackend.entities.templates.modifiers

import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.Entity

@Entity
abstract class ModifierTemplate<T : GameObject> : Template() {
    open fun modify(toBeModified: T): T {
        return toBeModified.also {
            it.suffix = suffix ?: it.suffix
            it.prefix = prefix ?: it.prefix
        }
    }

    open var suffix: String? = null
    open var prefix: String? = null
}
