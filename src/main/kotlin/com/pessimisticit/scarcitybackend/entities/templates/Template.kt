package com.pessimisticit.scarcitybackend.entities.templates


import com.fasterxml.jackson.annotation.JsonIgnore
import com.pessimisticit.scarcitybackend.configuration.converters.UriConverter
import org.springframework.hateoas.server.core.Relation
import java.net.URI
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 80)
@Relation("templates")
abstract class Template<T : Template<T>> {
    @Id
    open lateinit var id: UUID

    @Convert(converter = UriConverter::class)
    open lateinit var icon: URI

    open lateinit var description: String

    open lateinit var label: String

    open var flavor: String? = null

    @ManyToMany
    @JoinTable(
        name = "template_tags",
        joinColumns = [JoinColumn(name = "tags_id")],
        inverseJoinColumns = [JoinColumn(name = "template_id")]
    )
    open lateinit var _tags: Set<Tag>

    var tags: Collection<TagValue>
        @JsonIgnore
        get() = run { _tags.map { it.tag } }
        set(value) {
            _tags = value.map { Tag(it) }.toSet()
        }

    @Enumerated(EnumType.STRING)
    open var rarity: Rarity = Rarity.COMMON
}
