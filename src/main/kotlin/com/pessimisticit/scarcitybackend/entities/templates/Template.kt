package com.pessimisticit.scarcitybackend.entities.templates


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.pessimisticit.scarcitybackend.configuration.converters.UriConverter
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import org.springframework.hateoas.server.core.Relation
import java.net.URI
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 80)
@Relation("templates")
abstract class Template : HasRelativeRarity {
    @Id
    open lateinit var id: UUID

    @Convert(converter = UriConverter::class)
    open lateinit var icon: URI

    open lateinit var description: String

    open lateinit var label: String

    open var flavor: String? = null

    @ManyToMany
    @JsonManagedReference
    open lateinit var tag: Set<Tag>

    var tagValues: Collection<TagValue>
        @JsonIgnore
        get() = run { tag.map { it.tag } }
        set(value) {
            tag = value.map { Tag(it) }.toSet()
        }

    @Enumerated(EnumType.STRING)
    override var rarity: Rarity = Rarity.COMMON

    open var baseLevel: Double = 0.0
}
