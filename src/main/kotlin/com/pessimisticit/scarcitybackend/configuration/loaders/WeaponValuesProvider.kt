package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.equipment.BindType
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.*
import org.springframework.stereotype.Component
import java.net.URI

@Component
class WeaponValuesProvider : ValueProvider<Template<Weapon>> {
    override val getValues: Sequence<Template<Weapon>> = sequence {
        yield(
            Weapon().apply {
                description = """
                        |A short, broad sabre with a slightly curved blade sharpened on the cutting edge,
                        |and a hilt featuring a solid cupped guard.
                    """.trimMargin()
                label = "Cutlass"
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/MuseeMarine-sabre-p1000456.jpg/320px-MuseeMarine-sabre-p1000456.jpg")
                bindType = BindType.NEVER_BINDS
                weight = 1100.0
                damagePerTurn = 10.0
                damageShape = DamageShape.SLASHING
                damageType = DamageType.PHYSICAL
                range = 0.68
                swingDuration = 500
                readyDuration = 500
                slot = WeaponSlot.ONE_HAND
                itemLevel = 10.0
                rarity = Rarity.COMMON
                weaponType = WeaponType.SWORD
                tags = listOf(TagValue.AGE_OF_SAIL)
            }
        )
        yield(
            Weapon().apply {
                description = """
                        |A two-edged sword with a tapered point for stabbing during thrusting.
                        |A solid grip is provided by a knobbed hilt with ridges for the fingers.
                        |The previous owner's name is engraved on the blade.
                    """.trimMargin()
                label = "Gladius"
                flavor = "Quemadmodum gladius neminem occidit, occidentis telum est."
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Uncrossed_gladius.jpg/240px-Uncrossed_gladius.jpg")
                bindType = BindType.NEVER_BINDS
                weight = 905.0
                damagePerTurn = 11.0
                damageShape = DamageShape.PIERCING
                damageType = DamageType.PHYSICAL
                range = 0.58
                swingDuration = 500
                readyDuration = 300
                slot = WeaponSlot.ONE_HAND
                itemLevel = 11.0
                weaponType = WeaponType.SWORD
                rarity = Rarity.UNCOMMON
                tags = listOf(TagValue.ROMAN, TagValue.ANCIENT)
            }
        )
    }
}