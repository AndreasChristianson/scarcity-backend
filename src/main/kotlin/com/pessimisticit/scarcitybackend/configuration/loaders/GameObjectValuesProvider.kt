package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.repositories.UniverseTemplateRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class GameObjectValuesProvider(
    universeRepository: UniverseTemplateRepository
) : ValueProvider<GameObject<*>> {
    override val getValues: Sequence<GameObject<*>> = sequence {
        val bigBang = Created()
        val universe = GameObject<Universe>().apply {
            parent = this
            template = universeRepository.findAll().first()!!
            changes = listOf(bigBang)
            id = UUID.fromString("00000000-0000-0000-0000-000000000000")
        }
        with(bigBang) {
            id = UUID.fromString("00000000-0000-0000-0000-000000000000")
            parent = universe
            gameTime = 0
            source = universe
        }
        yield(universe)
    }
}