package com.pessimisticit.scarcitybackend.entities.changes


import com.fasterxml.jackson.annotation.JsonBackReference
import com.pessimisticit.scarcitybackend.entities.AbstractJpaPersistable
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Modifier
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "change")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Change : AbstractJpaPersistable() {
    @ManyToOne(targetEntity = GameEntity::class)
    @JoinColumn
    @JsonBackReference
    open lateinit var parent: GameEntity<*>

    open var gameTime: Long = 0

    @Temporal(TemporalType.TIMESTAMP)
    open var stamp: Date = Date()

    @ManyToOne(targetEntity = Modifier::class)
    @JoinColumn
    @JsonBackReference
    open var modifier: Modifier<*>? = null

    @ManyToOne(optional = true, targetEntity = GameEntity::class)
    @JoinColumn
    @JsonBackReference
    open var source: GameEntity<*>? = null

    abstract fun getChangeMessage(): String

    override fun toString(): String {
        return "$gameTime$ - ${this.javaClass.name}($id): ${getChangeMessage()}"
    }
}
