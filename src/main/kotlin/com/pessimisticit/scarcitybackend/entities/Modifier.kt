package com.pessimisticit.scarcitybackend.entities

import com.fasterxml.jackson.annotation.JsonProperty
import com.pessimisticit.scarcitybackend.entities.templates.HasTemplate
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "modifier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier<T : Template<T>> : HasTemplate {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    open var id: UUID? = null

    @ManyToOne(optional = false, targetEntity = Template::class)
    @JoinColumn(name = "template", nullable = false, updatable = false)
    override lateinit var template: Template<T>

    @ManyToOne(optional = false, targetEntity = GameObject::class)
    @JoinColumn(name = "parent", nullable = false, updatable = false)
    open lateinit var parent: GameObject<*>

    @Column(name = "value")
    open var value: Double? = null;

    open fun modify(toBeModified: GameObject<T>): GameObject<T> {
        return toBeModified;
    }
}
