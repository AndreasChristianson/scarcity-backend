package com.pessimisticit.scarcitybackend.entities.changes


import com.fasterxml.jackson.annotation.JsonIgnore
import com.pessimisticit.scarcitybackend.entities.GameObject
import org.springframework.hateoas.RepresentationModel
import java.time.Instant
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "change")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
class Change : RepresentationModel<Change>() {
    @Id
    var id: UUID = UUID.randomUUID()

    @ManyToOne(targetEntity = GameObject::class)
    @JoinColumn
    @JsonIgnore
    lateinit var gameObject: GameObject

    var gameTime: Long = 0

    var stamp: Instant = Instant.now()

    var message: String = ""

    override fun toString(): String {
        return "$gameTime|$stamp|${gameObject.id}: $message"
    }
}
