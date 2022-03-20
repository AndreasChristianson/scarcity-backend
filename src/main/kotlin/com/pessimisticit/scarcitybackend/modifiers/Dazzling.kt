package com.pessimisticit.scarcitybackend.modifiers

import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.abilities.evocations.EvokeLight
import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import java.net.URI
import javax.persistence.Entity

@LootableModifier(
    rarity = Rarity.NEVER,
    modifierType = ModifierType.HELPFUL,
    modifierTargets = [Weapon::class]
)
@Entity
class Dazzling : Fiery() {
    override val icon: URI
        get() = URI("http://example.com")
    override val description: String
        get() = "This weapon shines brightly, searing those it damages"
    override val name: String
        get() = "dazzling"

    override fun modifyAbilities(abilities: Sequence<Ability>): Sequence<Ability> {
        return abilities.plus(
            EvokeLight
        )
    }

    override fun modifyPrefix(prefix: String?): String {
        return "dazzling"
    }
}
