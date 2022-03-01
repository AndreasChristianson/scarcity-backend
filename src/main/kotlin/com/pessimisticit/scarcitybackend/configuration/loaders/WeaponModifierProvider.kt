package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.BindType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.*
import com.pessimisticit.scarcitybackend.mechanics.combat.ProcAction
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageShape
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageType
import com.pessimisticit.scarcitybackend.objects.Weapon
import org.springframework.stereotype.Component
import java.net.URI

@Component
class WeaponModifierProvider : ValueProvider<ModifierTemplate<Weapon>> {
    override val getValues: Sequence<ModifierTemplate<Weapon>> = sequence {
        yield(
            BonusDamageTemplate().apply {
                icon = URI("http://example.com")
                description = "Adds a small amount of fire damage"
                label = "Of Fire"
                rarity = Rarity.UNCOMMON
                baseLevel = 5.0
                damage = 5.0
                suffix = "of fire"
                damageType = DamageType.FIRE
                damageShape = DamageShape.PROXIMITY
            }
        )
        yield(
            BonusDamageTemplate().apply {
                icon = URI("http://example.com")
                description = "Blasts the target with light."
                label = "Blasting"
                rarity = Rarity.SCARCE
                baseLevel = 9.0
                damage = 9.0
                suffix = "of blasting"
                damageType = DamageType.ENERGY
                damageShape = DamageShape.PIERCING
                minBindType = BindType.BIND_ON_USE
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Never strikes true."
                label = "Bent"
                rarity = Rarity.COMMON
                prefix = "bent"
                baseLevel = -10.0
                damageMultiplier = 0.9
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Feels like and extension of your limb."
                label = "Perfectly weighted"
                rarity = Rarity.COMMON
                prefix = "mastercraft"
                baseLevel = 4.0
                damageMultiplier = 1.07
                swingTimeMultiplier = 0.93
                minBindType = BindType.BIND_ON_USE
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Connects like a truck, a slow, heavy truck."
                label = "Heavy"
                rarity = Rarity.COMMON
                prefix = "heavy"
                baseLevel = 0.0
                damageMultiplier = 1.1
                swingTimeMultiplier = 1.1
                weightMultiplier = 1.2
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Slightly faster that similar weapons."
                label = "Quick"
                rarity = Rarity.COMMON
                prefix = "quick"
                baseLevel = 2.0
                swingTimeMultiplier = 0.95
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Faster that similar weapons."
                label = "of speed"
                rarity = Rarity.UNCOMMON
                suffix = "of speed"
                baseLevel = 5.0
                swingTimeMultiplier = 0.9
                weightMultiplier = 0.95
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Noticeably faster that similar weapons."
                label = "Swift"
                rarity = Rarity.SCARCE
                prefix = "swift"
                baseLevel = 10.0
                swingTimeMultiplier = 0.85
                weightMultiplier = 0.9
                minBindType = BindType.BIND_ON_USE
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Much faster that similar weapons."
                label = "Rapid"
                rarity = Rarity.RARE
                suffix = "of rapidity"
                baseLevel = 20.0
                swingTimeMultiplier = 0.8
                weightMultiplier = 0.8
                minBindType = BindType.BIND_ON_USE
            }
        )
        yield(
            ScaleWeaponModifierTemplate().apply {
                icon = URI("http://example.com")
                description = "Heavier and slower than similar weapons"
                label = "Slow"
                rarity = Rarity.UNCOMMON
                prefix = "slow"
                baseLevel = -5.0
                swingTimeMultiplier = 1.1
                weightMultiplier = 1.2
            }
        )
        yield(
            WeaponProcTemplate().apply {
                icon = URI("http://example.com")
                description = "Upon hit, occasionally destroys a piece of equipment on the target."
                label = "Sundering"
                rarity = Rarity.SCARCE
                baseLevel = 10.0
                proc = ProcAction.DESTROY_EQUIPMENT
                prefix = "sundering"
                minBindType = BindType.BIND_ON_USE
            }
        )
        yield(
            DamageProcTemplate().apply {
                icon = URI("http://example.com")
                description = "Upon hit, occasionally deals extra freezing damage."
                label = "Freezing"
                rarity = Rarity.UNCOMMON
                baseLevel = 5.0
                damageType = DamageType.FROST
                damageShape = DamageShape.CONVECTION
                damage = 40.0
                procChance = .04
                proc = ProcAction.DEAL_DAMAGE
                prefix = "freezing"
            }
        )
        yield(
            DamageProcTemplate().apply {
                icon = URI("http://example.com")
                description = "Upon hit, occasionally deals extra fire damage."
                label = "Flaming"
                rarity = Rarity.UNCOMMON
                baseLevel = 5.0
                damageType = DamageType.FIRE
                damageShape = DamageShape.CONVECTION
                damage = 30.0
                procChance = .06
                proc = ProcAction.DEAL_DAMAGE
                prefix = "flaming"
            }
        )
    }
}