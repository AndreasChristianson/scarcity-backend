package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "modifier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier<V : Template<V>,T:ModifierTemplate<V,*>> {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: UUID? = null

    @ManyToOne(targetEntity = Template::class)
    @JoinColumn
    open lateinit var template: ModifierTemplate<V,*>

    @ManyToOne(targetEntity = GameObject::class)
    @JoinColumn
    @JsonBackReference
    open lateinit var parent: GameObject<V>

    abstract fun modify(toBeModified: GameObject<V>): GameObject<V>
}
