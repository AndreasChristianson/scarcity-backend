package com.pessimisticit.scarcitybackend.model

import com.pessimisticit.scarcitybackend.model.composers.HasTemplate
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "modifier")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier<T : GameObject<T>>(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open val id: UUID,

    @ManyToOne(optional = false)
    @JoinColumn(name = "template", nullable = false, updatable = false)
    override val template: Template<T>,

    @ManyToOne(optional = false)
    @JoinColumn(name = "parent", nullable = false, updatable = false)
    open val parent: GameObject<*>,

    @Column(name = "value")
    open val value: Double?,
):HasTemplate<T> {
}
