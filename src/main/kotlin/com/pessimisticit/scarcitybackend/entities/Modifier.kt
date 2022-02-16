package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "modifier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier<T : Template> {
    @Id
    open var id: UUID? = UUID.randomUUID()

    @ManyToOne(targetEntity = Template::class)
    @JoinColumn
    open lateinit var template: ModifierTemplate<T>

    @ManyToOne(targetEntity = GameObject::class)
    @JoinColumn
    @JsonBackReference
    open lateinit var parent: GameObject<T>

    abstract fun modify(toBeModified: GameObject<T>): GameObject<T>
}
