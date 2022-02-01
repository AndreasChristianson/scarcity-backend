package com.pessimisticit.scarcitybackend.model

import org.hibernate.annotations.GenericGenerator
import java.net.URI
import java.net.URL
import java.util.*
import javax.persistence.*

@Entity
@Table(name="template")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Template(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open val id: UUID,
    @Column(name="icon")
    open val icon: URL,
    @Column(name="description")
    open val description: String,
    @Column(name="label")
    open val label: String,
)
