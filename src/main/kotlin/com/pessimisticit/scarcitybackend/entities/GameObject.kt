package com.pessimisticit.scarcitybackend.entities

import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.entities.templates.HasTemplate
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class GameObject<T : Template<T>> : HasTemplate {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: UUID? = null

    @ManyToOne(optional = false, targetEntity = Template::class)
    @JoinColumn(name = "template", nullable = false, updatable = false)
    override lateinit var template: Template<T>

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent", nullable = true, updatable = true)
    open var parent: GameObject<*>? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", targetEntity = GameObject::class)
    open lateinit var children: Collection<GameObject<*>>

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", targetEntity = Change::class)
    open lateinit var changes: Collection<Change>

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", targetEntity = Modifier::class)
    open lateinit var modifiers: Collection<Modifier<T>>

    fun applyModifiers(): GameObject<T> {
        return modifiers.fold(this) { acc, modifier -> modifier.modify(acc) }
    }
}
