package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.springframework.data.rest.core.annotation.RestResource
import java.util.stream.Stream
import javax.persistence.EntityManager


class TemplateGeneratorRepositoryImpl(
//    private val sessionFactory: SessionFactory,
    private val entityManager: EntityManager
) : TemplateGeneratorRepository {
    @RestResource(exported = false)
    override fun <T : Template<T>> getPotentialTemplates(
        entityClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
        requiredTags: Collection<TagValue>
    ): Stream<T> {
        val validRarities = Rarity.values()
            .filter { it.relativeWeight <= minRarity.relativeWeight }
            .toList()
        val query = when {
            requiredTags.isEmpty() ->

                entityManager
                    .createQuery(
                        """
                                SELECT t
                                FROM ${entityClass.name} t 
                                WHERE t.rarity IN (:validRarities)
                                AND t.itemLevel BETWEEN :itemLevelMin AND :itemLevelMax
                                """, entityClass
                    )
            else ->
                entityManager
                    .createQuery(
                        """
                        SELECT distinct t
                        FROM ${entityClass.name} t
                        JOIN t._tags tag
                        WHERE t.rarity IN (:validRarities)
                        AND t.itemLevel :itemLevelMin AND :itemLevelMax
                        AND (tag.tag in (:requiredTags))
                        """, entityClass
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