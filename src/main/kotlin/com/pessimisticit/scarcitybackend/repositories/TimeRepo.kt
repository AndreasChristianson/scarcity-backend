package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.Time
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class TimeRepo(
    @PersistenceContext
    val em: EntityManager
) {
    fun getGameTime(): Long {
        return em.find(Time::class.java, UUID.fromString("00000000-0000-0000-0000-000000000000"))
            .gameTime
    }
}
