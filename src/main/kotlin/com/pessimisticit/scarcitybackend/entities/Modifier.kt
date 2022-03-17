package com.pessimisticit.scarcitybackend.entities


import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier : AbstractJpaPersistable(), Displayable {
    open val priority: Double
        get() = 0.0

    open fun modifyDamageSpecifications(damageSpecification: Sequence<DamageSpecification>) = damageSpecification
    open fun modifySuffix(suffix: String?) = suffix
    open fun modifyPrefix(prefix: String?) = prefix
    open fun modifyRarity(rarity: Rarity) = rarity
    open fun modifyDurability(durability: Double) = durability
    open fun modifyWeight(weight: Double) = weight
    open fun modifySwingResetDuration(swingResetDuration: Long) = swingResetDuration
    open fun modifyDps(dps: Double) = dps
    open fun modifyAbilities(abilities: Sequence<Ability>):Sequence<Ability> = abilities
}
