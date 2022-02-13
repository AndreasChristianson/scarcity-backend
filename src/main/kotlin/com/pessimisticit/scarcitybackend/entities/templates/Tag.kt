package com.pessimisticit.scarcitybackend.entities.templates


import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.*
import javax.persistence.*

@Entity
class Tag(
    value: TagValue
) {
    @Id
    var id: UUID = UUID.nameUUIDFromBytes(value.name.toByteArray());

    @Enumerated(EnumType.STRING)
    var tag: TagValue = value

    @ManyToMany(targetEntity = Template::class, mappedBy = "_tags")
    lateinit var templates: Collection<Template<*>>
}
