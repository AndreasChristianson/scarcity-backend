package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entropy.ItemGenerator
import com.pessimisticit.scarcitybackend.entropy.LootableModifier
import com.pessimisticit.scarcitybackend.entropy.Table
import org.springframework.stereotype.Component


@Component
class AllModifiers : AnnotationCollector<LootableModifier>() {
    fun <T> getTableForTargetClass(targetClass: Class<T>): Table<Modifier> {
        val asGenerators = allMatches
            .filter {
                it.first.modifierTargets.any { modifierTarget ->
                    modifierTarget.java.isAssignableFrom(targetClass)
                }
            }
            .map {
                ItemGenerator<Modifier>(
                    level = it.first.modifierType.baseLevel,
                    rarity = it.first.rarity,
                    tags = it.first.tags.asSequence(),
                    generator = { it.second.getConstructor().newInstance() as Modifier },
                )
            }
        return Table(asGenerators)
    }


    override val annotationTarget: Class<out LootableModifier>
        get() = LootableModifier::class.java
}
