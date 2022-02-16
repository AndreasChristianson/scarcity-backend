package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.springframework.data.rest.core.annotation.RestResource
import java.util.stream.Stream

interface TemplateGeneratorRepository {
    @RestResource(exported = false)
    fun <T : Template> getPotentialTemplates(
        equipmentClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
        requiredTags: Collection<TagValue>
    ): Stream<T>
}