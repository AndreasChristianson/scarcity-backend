package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.springframework.stereotype.Repository
import java.util.stream.Stream
import javax.persistence.EntityManager


@Repository
class TemplateGeneratorRepositoryImpl(
    private val entityManager: EntityManager
) : TemplateGeneratorRepository {
    override fun <T : Template> getPotentialTemplates(
        equipmentClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
        requiredTags: Collection<TagValue>
    ): Stream<out T> {
        val validRarities = Rarity.values()
            .filter { it.relativeWeight <= minRarity.relativeWeight }
            .toList()
        val query = when {
            requiredTags.isEmpty() ->

                entityManager
                    .createQuery(
                        """
                        SELECT t
                        FROM ${equipmentClass.name} t 
                        WHERE t.rarity IN (:validRarities)
                        AND t.baseLevel BETWEEN :itemLevelMin AND :itemLevelMax
                        """, equipmentClass
                    )
            else ->
                entityManager
                    .createQuery(
                        """
                        SELECT distinct t
                        FROM ${equipmentClass.name} t
                        JOIN t._tags tag
                        WHERE t.rarity IN (:validRarities)
                        AND t.baseLevel :itemLevelMin AND :itemLevelMax
                        AND (tag.tag in (:requiredTags))
                        """, equipmentClass
                    )
                    .setParameter("requiredTags", requiredTags)

        }
        return query
            .setParameter("itemLevelMin", itemLevelMin)
            .setParameter("itemLevelMax", itemLevelMax)
            .setParameter("validRarities", validRarities)
            .resultStream
    }
}