package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.GameObject
import org.springframework.stereotype.Component

@Component
class GameObjectValuesProvider(
) : ValueProvider<GameObject<*>> {
    override val getValues: Sequence<GameObject<*>> = sequence {

    }
}