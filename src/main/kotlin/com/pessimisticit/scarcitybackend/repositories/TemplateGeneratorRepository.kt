package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.objects.GameObject
import java.util.stream.Stream

interface TemplateGeneratorRepository {
    fun <T : GameObject> getPotentialGameObjectTemplates(
        templateClass: Class<out GameObjectTemplate<T>>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
        requiredTags: Collection<TagValue>
    ): Stream<out GameObjectTemplate<T>>

    fun <T : GameObject> getPotentialModifierTemplates(
        templateClass: Class<out ModifierTemplate<T>>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
    ): Stream<out ModifierTemplate<T>>
}