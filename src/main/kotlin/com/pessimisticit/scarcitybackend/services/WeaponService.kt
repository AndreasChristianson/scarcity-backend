package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entropy.Roller
import com.pessimisticit.scarcitybackend.entropy.filterByItemLevel
import com.pessimisticit.scarcitybackend.entropy.filterByTag
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.random.Random

@Service
class WeaponService(
    val gameObjectService: GameObjectService,
    val allTemplates: AllTemplates,
    val allModifiers: AllModifiers,
) {
    private fun createEntropySource(raritySkew: Double?): (Double) -> Double =
        if (raritySkew != null) {
            { max ->
                Roller.truncatedGaussian(
                    center = raritySkew * max,
                    min = 0.0,
                    max = max,
                    stdev = max * 0.3
                )
            }
        } else {
            { max -> Random.nextDouble(max) }
        }

    @Transactional
    fun <T : GameObject> generate(
        targetClass: Class<T>,
        itemLevelMax: Double,
        itemLevelMin: Double,
        modifierQualitySkew: Double?,
        modifierQuantitySkew: Double?,
        raritySkew: Double?,
        requiredTag: Tag? = null,
    ): T? {
        val table = allTemplates.getTableForClass(targetClass)
        val filteredTable = table
            .filterByItemLevel(itemLevelMin, itemLevelMax)
            .filterByTag(requiredTag)
        val selected = filteredTable.select(createEntropySource(raritySkew))
            ?: return null

        val saved = gameObjectService.createGameObject(
            gameObject = selected
        )

        val modifierTable = allModifiers.getTableForTargetClass(targetClass)
        val modifierCount = Roller.truncatedGaussianInt(
            min = 0,
            center = modifierQuantitySkew ?: 0.0
        )
        val modifiers = modifierTable.selectMultiple(modifierCount, createEntropySource(modifierQualitySkew))

        val withModifiers = gameObjectService.addModifiers(
            saved,
            modifiers
        )
        return withModifiers

    }
}

