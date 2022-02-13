package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_object")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
class GameObject<T : Template<T>> {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    var id: UUID? = null

    @ManyToOne(optional = false, targetEntity = Template::class)
    lateinit var template: Template<T>

    @ManyToOne(optional = true)
    @JsonBackReference
    var parent: GameObject<*>? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = GameObject::class)
    lateinit var children: Collection<GameObject<*>>

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Change::class, cascade = [CascadeType.PERSIST])
    lateinit var changes: Collection<Change>

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Modifier::class)
    lateinit var modifiers: Collection<Modifier<T, *>>

    fun applyModifiers(): GameObject<T> {
        return modifiers.fold(this) { acc, modifier -> modifier.modify(acc) }
    }
}
