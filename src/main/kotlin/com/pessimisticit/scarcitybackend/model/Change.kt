package com.pessimisticit.scarcitybackend.model

import org.hibernate.annotations.GenericGenerator
import java.net.URI
import java.net.URL
import java.util.*
import javax.persistence.*

@Entity
@Table(name="change")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Change(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open val id: UUID,

    @ManyToOne(optional=false)
    @JoinColumn(name="parent", nullable=false, updatable=true)
    open val parent: GameObject<*>,

    @ManyToOne(optional=true)
    @JoinColumn(name="modifier", nullable=true, updatable=true)
    open val modifier: Modifier<*>,

    @ManyToOne(optional=true)
    @JoinColumn(name="source", nullable=true, updatable=true)
    open val source: GameObject<*>,
){}
