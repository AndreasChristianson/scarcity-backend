package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.*

@Entity
@Table(name = "game_object")
class GameEntity<T : GameObject> : TemplatedEntity<GameObjectTemplate<T>>() {
    @ManyToOne(optional = true)
    @JsonBackReference
    lateinit var parent: GameEntity<*>

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = GameEntity::class, cascade = [CascadeType.ALL])
    lateinit var children: MutableCollection<GameEntity<*>>

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Change::class, cascade = [CascadeType.ALL])
    lateinit var changes: MutableCollection<Change>

    @JsonManagedReference
    @OneToMany(mappedBy = "parent", targetEntity = Modifier::class, cascade = [CascadeType.ALL])
    var modifiers: MutableCollection<Modifier<T>> = mutableListOf()

    @delegate:Transient
    @get:Transient
    val computed: T by lazy {
        val baseInstance = template.generateInstance()
        modifiers
            .sortedBy { it.template.baseLevel }
            .fold(baseInstance) { instance, modifier ->
            modifier.modify(instance)
        }
    }
}

