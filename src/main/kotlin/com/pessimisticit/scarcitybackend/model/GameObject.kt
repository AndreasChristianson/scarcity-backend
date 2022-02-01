package com.pessimisticit.scarcitybackend.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name="game_object")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype", discriminatorType = DiscriminatorType.STRING)
abstract class GameObject(
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open val id: UUID,
    @ManyToOne(optional=false)
    @JoinColumn(name="template", nullable=false, updatable=false)
    open val template: Template,
    @ManyToOne(optional=true)
    @JoinColumn(name="parent", nullable=true, updatable=true)
    open val parent: GameObject?,
){
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "parent")
    open val children = mutableListOf<GameObject>()
}
