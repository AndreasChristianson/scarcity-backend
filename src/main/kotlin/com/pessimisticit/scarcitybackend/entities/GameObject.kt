package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import org.springframework.hateoas.RepresentationModel
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class GameObject : Displayable, RepresentationModel<GameObject>() {
    @Id
    open var id: UUID = UUID.randomUUID()

    @ManyToOne
    @JsonIgnore
    open lateinit var parent: GameObject

    @JsonIgnore
    @OneToMany(
        mappedBy = "parent",
        targetEntity = GameObject::class
    )
    open var children: MutableCollection<GameObject> = mutableListOf()

    @JsonIgnore
    @OneToMany(
        mappedBy = "gameObject",
        targetEntity = Change::class
    )
    open var changes: MutableCollection<Change> = mutableListOf()

    @JsonIgnore
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "parent",
        targetEntity = Modifier::class,
    )
    open var modifiers: MutableCollection<Modifier> = mutableListOf()

    protected open val baseModifiers: Sequence<Modifier>
        get() = sequenceOf()

    fun <T> applyModifiers(value: T, funct: (Modifier, T) -> T): T =
        (modifiers.asSequence().sortedBy { it.priority } + baseModifiers)
            .fold(value) { acc, mod -> funct.invoke(mod, acc) }

    override fun toString(): String {
        return "$displayName ($id)"
    }

    val displayName
        get() = sequenceOf(prefix, name, suffix)
            .filterNotNull()
            .joinToString(" ")

    private val prefix
        get() = applyModifiers(null, Modifier::modifyPrefix)
    private val suffix
        get() = applyModifiers(null, Modifier::modifySuffix)
    val abilities
        get() = applyModifiers(sequenceOf(), Modifier::modifyAbilities)

}
