package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.constants.WeaponSlot
import com.pessimisticit.scarcitybackend.constants.WeaponType
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import org.springframework.stereotype.Component
import java.net.URI

@Component
class WeaponValuesProvider : ValueProvider<WeaponTemplate> {
    override val getValues: Sequence<WeaponTemplate> = sequence {
        yield(
            WeaponTemplate().apply {
                description = """
                        |A short, broad sabre with a slightly curved blade sharpened on the cutting edge,
                        |and a hilt featuring a solid cupped guard.
                    """.trimMargin()
                label = "cutlass"
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/MuseeMarine-sabre-p1000456.jpg/320px-MuseeMarine-sabre-p1000456.jpg")
                weight = 1100.0
                damageShape = DamageShape.SLASHING
                damageType = DamageType.PHYSICAL
                maxRange = 0.68
                swingTimeout = 1000
                slot = WeaponSlot.ONE_HAND
                baseLevel = 10.0
                rarity = Rarity.COMMON
                weaponType = WeaponType.SWORD
                tagValues = listOf(TagValue.AGE_OF_SAIL)
            }
        )
        yield(
            WeaponTemplate().apply {
                description = """
                        |A two-edged sword with a tapered point for stabbing during thrusting.
                        |A solid grip is provided by a knobbed hilt with ridges for the fingers.
                        |The previous owner's name is engraved on the blade.
                    """.trimMargin()
                label = "gladius"
                flavor = "Quemadmodum gladius neminem occidit, occidentis telum est."
                icon =
                    URI("https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Uncrossed_gladius.jpg/240px-Uncrossed_gladius.jpg")
                weight = 905.0
                damageShape = DamageShape.PIERCING
                damageType = DamageType.PHYSICAL
                maxRange = 0.58
                swingTimeout = 800
                slot = WeaponSlot.ONE_HAND
                baseLevel = 10.0
                weaponType = WeaponType.SWORD
                rarity = Rarity.UNCOMMON
                tagValues = listOf(TagValue.ROMAN, TagValue.ANCIENT)
            }
        )
        yield(
            WeaponTemplate().apply {
                description = """
                        |A simple wooden club made of little more than a branch.
                    """.trimMargin()
                label = "virge"
                icon =
                    URI("https://example.com")
                weight = 800.0
                damageShape = DamageShape.BLUDGEONING
                damageType = DamageType.PHYSICAL
                maxRange = 0.45
                swingTimeout = 700
                slot = WeaponSlot.ONE_HAND
                baseLevel = 7.0
                weaponType = WeaponType.BLUDGEON
                rarity = Rarity.UNCOMMON
                tagValues = listOf(TagValue.RELIGIOUS)
            }
        )
        yield(
            WeaponTemplate().apply {
                description = """
                        |A flanged mace with six steal feathers.
                    """.trimMargin()
                label = "pernach"
                icon =
                    URI("https://example.com")
                weight = 1200.0
                damageShape = DamageShape.BLUDGEONING
                damageType = DamageType.PHYSICAL
                maxRange = 0.60
                swingTimeout = 1200
                slot = WeaponSlot.ONE_HAND
                baseLevel = 27.0
                weaponType = WeaponType.BLUDGEON
                rarity = Rarity.UNCOMMON
                tagValues = listOf(TagValue.EUROPEAN)
            }
        )
        yield(
            WeaponTemplate().apply {
                description = """
                        |A longer, heavier pernach.
                    """.trimMargin()
                label = "shestopior"
                icon =
                    URI("https://example.com")
                weight = 1600.0
                damageShape = DamageShape.BLUDGEONING
                damageType = DamageType.PHYSICAL
                maxRange = 0.67
                swingTimeout = 1500
                slot = WeaponSlot.ONE_HAND
                baseLevel = 36.0
                weaponType = WeaponType.BLUDGEON
                rarity = Rarity.UNCOMMON
                tagValues = listOf(TagValue.EUROPEAN)
            }
        )
        yield(
            WeaponTemplate().apply {
                description = """
                        |A simple improvised mace.
                    """.trimMargin()
                label = "bludgeon"
                icon =
                    URI("https://example.com")
                weight = 1400.0
                damageShape = DamageShape.BLUDGEONING
                damageType = DamageType.PHYSICAL
                maxRange = 0.56
                swingTimeout = 1100
                slot = WeaponSlot.ONE_HAND
                baseLevel = 15.0
                weaponType = WeaponType.BLUDGEON
                rarity = Rarity.UNCOMMON
                tagValues = listOf(TagValue.EUROPEAN)
            }
        )
    }
}