package com.pessimisticit.scarcitybackend.entities.rooms


import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI
import javax.persistence.Entity

@Entity
class Universe : GameObject() {
    override val icon: GameIcon
        get() = SvgIcon(URI("$svgRoot/galaxy.svg"),"white")
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
