package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entropy.Roller
import com.pessimisticit.scarcitybackend.entropy.Table
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class WeaponService (
    val instantiator: TemplateInstantiator,
    val allTemplates: AllTemplates
        ){
    fun <T: GameEntity> generate(
        targetClass: Class<T>,
        itemLevelMax: Double,
        itemLevelMin: Double,
//        modifierQualitySkew: Double,
//        modifierQuantitySkew: Double,
        raritySkew: Double?,
        requiredTag: Tag? = null,
    ): T? {
        val table = allTemplates.getTableForClass(targetClass)
        val filteredTable = table.filter {
            it.level > itemLevelMin
                    && it.level < itemLevelMax
                    && it.tags.contains(requiredTag)

        }
        val randomDoubleFunction: (Double) -> Double =
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
        val selected = filteredTable.select { randomDoubleFunction(it) }
            ?: return null
        //todo: modifiers...
        return instantiator.instanciateGameEntity(
            unsaved = selected
        )
    }

}

