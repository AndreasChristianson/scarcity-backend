package com.pessimisticit.scarcitybackend.entities.templates


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.Tag
import com.pessimisticit.scarcitybackend.objects.GameObject
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToMany

@Entity
abstract class GameObjectTemplate<T : GameObject> : Template() {
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonManagedReference
    open lateinit var tag: Set<Tag>

    var tagValues: Collection<TagValue>
        @JsonIgnore
        get() = run { tag.map { it.tag } }
        set(value) {
            tag = value.map { Tag(it) }.toSet()
        }

    abstract fun generateInstance(): T
}
