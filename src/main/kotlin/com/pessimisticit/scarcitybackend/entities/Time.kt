package com.pessimisticit.scarcitybackend.entities

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "game_time")
class Time {
    @Id
    open var id: UUID? = UUID.randomUUID()

    @Column
    open var gameTime: Long = 0
}
