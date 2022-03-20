package com.pessimisticit.scarcitybackend.entities


import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import com.pessimisticit.scarcitybackend.mechanics.damage.DamageSpecification
import org.springframework.hateoas.RepresentationModel
import java.util.*
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
abstract class Modifier : Displayable {
    @Id
    open var id: UUID = UUID.randomUUID()

    @ManyToOne
    @JsonIgnore
    open lateinit var parent: GameObject

    open val priority: Double
        get() = 0.0

    open fun modifyDamageSpecifications(damageSpecifications: Sequence<DamageSpecification>) = damageSpecifications
    open fun modifySuffix(suffix: String?) = suffix
    open fun modifyPrefix(prefix: String?) = prefix
    open fun modifyDurability(durability: Double) = durability
    open fun modifyWeight(weight: Double) = weight
    open fun modifySwingResetDuration(swingResetDuration: Long) = swingResetDuration
    open fun modifyDps(dps: Double) = dps
    open fun modifyAbilities(abilities: Sequence<Ability>): Sequence<Ability> = abilities
    override fun toString(): String {
        return "$name ($id)"
    }
}
