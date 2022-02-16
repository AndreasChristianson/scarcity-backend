package com.pessimisticit.scarcitybackend.entities.templates


import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.constants.TagValue
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

    @JsonBackReference
    @ManyToMany(targetEntity = Template::class, mappedBy = "tag")
    lateinit var template: Collection<Template>
}
