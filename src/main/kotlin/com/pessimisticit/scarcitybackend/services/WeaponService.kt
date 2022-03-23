package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entropy.GenerationProfile
import com.pessimisticit.scarcitybackend.entropy.filterByItemLevel
import com.pessimisticit.scarcitybackend.entropy.filterByTag
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class WeaponService(
    val gameObjectService: GameObjectService,
    val allTemplates: AllTemplates,
    val allModifiers: AllModifiers,
) {
    @Transactional
    fun <T : GameObject> generate(
        targetClass: Class<T>,
        itemLevelMax: Double,
        itemLevelMin: Double,
        profile: GenerationProfile,
        requiredTag: Tag? = null,
    ): T? {
        val table = allTemplates.getTableForClass(targetClass)
        val filteredTable = table
            .filterByItemLevel(itemLevelMin, itemLevelMax)
            .filterByTag(requiredTag)
        val selected = filteredTable.select(profile.entropySource)
            ?: return null

        val saved = gameObjectService.createGameObject(
            gameObject = selected
        )

        val modifierTable = allModifiers.getTableForTargetClass(saved.javaClass)
        val modifierCount = profile.modifierCountSource.invoke()
        val modifiers = profile.modifierFilter(modifierTable)
            .selectMultiple(modifierCount, profile.entropySource)

        val withModifiers = gameObjectService.addModifiers(
            saved,
            modifiers
        )
        return withModifiers

    }
}

