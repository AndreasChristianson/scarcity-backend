package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.BonusDamageTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.DamageProcTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponProcTemplate
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
                rarity = Rarity.COMMON
                baseLevel = 5.0
                damage = 5.0
                suffix = "of fire"
                damageType = DamageType.FIRE
                damageShape = DamageShape.PROXIMITY
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