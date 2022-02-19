package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.DamageShape
import com.pessimisticit.scarcitybackend.constants.DamageType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.BonusDamage
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.objects.Weapon
import org.springframework.stereotype.Component
import java.net.URI

@Component
class WeaponModifierProvider : ValueProvider<ModifierTemplate<Weapon>> {
    override val getValues: Sequence<ModifierTemplate<Weapon>> = sequence {
        yield(
            BonusDamage().apply {
                icon = URI("http://example.com")
                description = "Adds a small amount of fire damage"
                label = "Of Fire"
                rarity = Rarity.COMMON
                baseLevel = 5.0
                doubleValue = 5.0
                stringValue = "of fire"
                damageType = DamageType.FIRE
                damageShape = DamageShape.PROXIMITY
            }
        )
    }
}