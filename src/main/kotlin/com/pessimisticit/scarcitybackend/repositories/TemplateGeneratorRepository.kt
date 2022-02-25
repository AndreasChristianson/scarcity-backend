package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.constants.Rarity
import java.util.stream.Stream

interface TemplateGeneratorRepository {
    fun <T> getTemplates(
        templateClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        rarity: Rarity,
        ): Stream<T>
    fun <T> getTemplateByLabel(
        templateClass: Class<T>,
        label: String,
    ): T?
}
