package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.rooms.Universe
import org.springframework.stereotype.Component
import java.util.*

@Component
class MiscValuesProvider : ValueProvider<GameObject> {
    override val getValues: Sequence<GameObject> = sequence {
        yield(
            Universe()
                .apply {
                    id = UUID.fromString("00000000-0000-0000-0000-000000000000")
//                    parent = this
                }
        )
    }
}