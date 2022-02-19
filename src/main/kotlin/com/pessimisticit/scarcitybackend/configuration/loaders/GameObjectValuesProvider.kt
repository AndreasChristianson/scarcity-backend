package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.objects.Universe
import com.pessimisticit.scarcitybackend.repositories.UniverseTemplateRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class GameObjectValuesProvider(
    universeTemplateRepository: UniverseTemplateRepository
) : ValueProvider<GameEntity<*>> {
    override val getValues: Sequence<GameEntity<*>> = sequence {
        val bigBang = Created()
        val universe = GameEntity<Universe>().apply {
            parent = this
            template = universeTemplateRepository.getUniverseTemplate()
            changes = mutableListOf(bigBang)
            id = UUID.fromString("00000000-0000-0000-0000-000000000000")
        }
        with(bigBang) {
            id = UUID.fromString("00000000-0000-0000-0000-000000000000")
            parent = universe
            gameTime = 0
            source = universe
            stamp = Date(0L)
        }
        yield(universe)
    }
}