package com.pessimisticit.scarcitybackend.model

import com.pessimisticit.scarcitybackend.model.composers.HasTemplate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class GameObject<T : GameObject<T>>(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open val id: UUID,

    @ManyToOne(optional = false)
    @JoinColumn(name = "template", nullable = false, updatable = false)
    override val template: Template<T>,

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent", nullable = true, updatable = true)
    open val parent: GameObject<*>?,
) : HasTemplate<T>  {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    open val children = mutableListOf<GameObject<*>>()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    open val changes = mutableListOf<Change>()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    open val modifiers = mutableListOf<Modifier<T>>()
}
