package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.entities.templates.equipment.BindType
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.*
import org.springframework.stereotype.Component
import java.net.URI

@Component
class MiscValuesProvider : ValueProvider<Template<*>> {
    override val getValues: Sequence<Template<*>> = sequence {
        yield(
            Universe().apply {
                description = """
                        | A container for all games, and orphaned objects.
                    """.trimMargin()
                label = "The Universe"
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Sombrero_Galaxy_in_infrared_light_%28Hubble_Space_Telescope_and_Spitzer_Space_Telescope%29.jpg/320px-Sombrero_Galaxy_in_infrared_light_%28Hubble_Space_Telescope_and_Spitzer_Space_Telescope%29.jpg")
            }
        )
    }
}