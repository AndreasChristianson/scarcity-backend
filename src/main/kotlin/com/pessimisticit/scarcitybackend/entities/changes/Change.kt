package com.pessimisticit.scarcitybackend.entities.changes


import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.Modifier
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "change")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Change {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: UUID? = null

    @ManyToOne(optional = false, targetEntity = GameObject::class)
    @JoinColumn(name = "parent", nullable = false, updatable = true)
    open lateinit var parent: GameObject<*>

    @Column(name = "game_time", nullable = false)
    open var gameTime: Long = 0

    @ManyToOne(optional = true, targetEntity = Modifier::class)
    @JoinColumn(name = "modifier", nullable = true, updatable = true)
    open var modifier: Modifier<*>? = null

    @ManyToOne(optional = true, targetEntity = GameObject::class)
    @JoinColumn(name = "source", nullable = true, updatable = true)
    open var source: GameObject<*>? = null

    @Column(name = "stamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    open lateinit var stamp: Date

    abstract fun getChangeMessage(): String;

    override fun toString(): String {
        return "$gameTime$ - ${this.javaClass.name}($id): ${getChangeMessage()}"
    }
}
