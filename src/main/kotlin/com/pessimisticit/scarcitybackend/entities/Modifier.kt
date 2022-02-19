package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.*

@Entity
@Table(name = "modifier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
class Modifier<T : GameObject> : TemplatedEntity<ModifierTemplate<T>>() {

    @ManyToOne(targetEntity = GameEntity::class)
    @JoinColumn
    @JsonBackReference
    open lateinit var parent: GameEntity<T>

    fun modify(toBeModified: T): T {
        return template.modify(toBeModified)
    }
}
