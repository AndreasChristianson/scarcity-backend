package com.pessimisticit.scarcitybackend.entities.templates


import com.pessimisticit.scarcitybackend.configuration.converters.UriConverter
import org.hibernate.annotations.GenericGenerator
import java.net.URI
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Template<T : Template<T>> {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: UUID? = null

    @Column( nullable = false)
    @Convert(converter = UriConverter::class)
    open lateinit var icon: URI

    @Column(nullable = false)
    open lateinit var description: String

    @Column(nullable = false)
    open lateinit var label: String

    @ElementCollection(targetClass = Tag::class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "template_tag")
    @Column(name = "tag")
    open lateinit var tags: Collection<Tag>

//    abstract fun generate(): GameObject<T>
}
