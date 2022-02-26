package com.pessimisticit.scarcitybackend.entities.templates


import com.pessimisticit.scarcitybackend.configuration.converters.UriConverter
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.AbstractJpaPersistable
import org.springframework.hateoas.server.core.Relation
import java.net.URI
import javax.persistence.*

@Entity
@Table(name = "template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING, length = 80)
@Relation("templates")
abstract class Template : HasRelativeRarity, AbstractJpaPersistable() {
    @Convert(converter = UriConverter::class)
    open lateinit var icon: URI

    open lateinit var description: String

    open lateinit var label: String

    open var flavor: String? = null

    @Enumerated
    override var rarity: Rarity = Rarity.COMMON

    open var baseLevel: Double = 0.0

    override fun toString(): String {
        return "$label template"
    }
}
