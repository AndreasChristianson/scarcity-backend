package com.pessimisticit.scarcitybackend.entities


import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import java.util.*
import javax.persistence.*

@Entity
class Tag(
    value: TagValue
) {
    @Id
    var id: UUID = UUID.nameUUIDFromBytes(value.name.toByteArray())

    @Enumerated(EnumType.STRING)
    var tag: TagValue = value

    @JsonBackReference
    @ManyToMany(targetEntity = GameObjectTemplate::class, mappedBy = "tag")
    lateinit var template: Collection<GameObjectTemplate<*>>
}
