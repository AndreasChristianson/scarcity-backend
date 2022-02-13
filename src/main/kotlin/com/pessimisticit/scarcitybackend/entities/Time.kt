package com.pessimisticit.scarcitybackend.entities

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "game_time")
class Time {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
        name = "uuid",
        strategy = "org.hibernate.id.UUIDGenerator",
    )
    open var id: UUID? = null

    @Column
    open var gameTime: Long = 0
}
