package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.UniverseTemplate
import org.springframework.stereotype.Component
import java.net.URI

@Component
class MiscValuesProvider : ValueProvider<Template> {
    override val getValues: Sequence<Template> = sequence {
        yield(
            UniverseTemplate().apply {
                description = """
                        | A container for all games, and orphaned objects.
                    """.trimMargin()
                label = "The Universe"
                rarity = Rarity.EXTRAORDINARY
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Sombrero_Galaxy_in_infrared_light_%28Hubble_Space_Telescope_and_Spitzer_Space_Telescope%29.jpg/320px-Sombrero_Galaxy_in_infrared_light_%28Hubble_Space_Telescope_and_Spitzer_Space_Telescope%29.jpg")
            }
        )
    }
}