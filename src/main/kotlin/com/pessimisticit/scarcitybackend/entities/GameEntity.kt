package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import java.net.URI
import javax.persistence.*
import kotlin.reflect.KProperty0

@Entity
@Table(name = "game_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class GameEntity : AbstractJpaPersistable() , Displayable {
    @ManyToOne
    @JsonBackReference
    open lateinit var parent: GameEntity

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = GameEntity::class, cascade = [CascadeType.PERSIST])
    open var children: MutableCollection<GameEntity> = mutableListOf()

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Change::class, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open var changes: MutableCollection<Change> = mutableListOf()

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Modifier::class, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    open var modifiers: MutableCollection<Modifier> = mutableListOf()

    open val baseModifiers: Sequence<Modifier>
        get() = sequenceOf()

    fun <T> applyModifiers(value: T, funct: (Modifier, T) -> T): T =
        (modifiers.asSequence().sortedBy { it.priority } + baseModifiers)
            .fold(value) { acc, mod -> funct.invoke(mod, acc) }

    override fun toString(): String {
        return "$name ($id)"
    }

//    private val modificationSum: Double
//        get() = modifiers.sumOf { it.template.baseLevel }
//
//    private val modificationMax: Double
//        get() = modifiers.maxOfOrNull { it.template.baseLevel } ?: 0.0

//    @delegate:Transient
//    @get:Transient
//    val computed: T by lazy {
//        val baseInstance = template.generateInstance().also {
//            it.modificationSum = modificationSum
//            it.modificationMax = modificationMax
//        }
//
//        modifiers
//            .sortedBy { abs(it.template.baseLevel) }
//            .fold(baseInstance) { instance, modifier ->
//                modifier.modify(instance)
//            }
//    }
}

