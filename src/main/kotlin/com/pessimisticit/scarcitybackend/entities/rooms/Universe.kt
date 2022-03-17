package com.pessimisticit.scarcitybackend.entities.rooms


import com.pessimisticit.scarcitybackend.entities.GameEntity
import java.net.URI
import javax.persistence.Entity

@Entity
class Universe : GameEntity() {
    override val icon: URI
        get() = URI("http://some-galaxy.image")
    override val description: String
        get() = "A container for all games, and orphaned objects."

    override val name: String
        get() = "The Universe"
    override val flavor: String
        get() = """
            |Two possibilities exist:
            |either we are alone in the Universe or we are not.
            |Both are equally terrifying.
            |""".trimMargin()
}
