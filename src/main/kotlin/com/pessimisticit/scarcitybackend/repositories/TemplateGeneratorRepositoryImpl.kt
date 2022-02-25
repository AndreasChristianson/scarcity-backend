package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.constants.Rarity
import org.springframework.stereotype.Repository
import java.util.stream.Stream
import javax.persistence.EntityManager

@Repository
class TemplateGeneratorRepositoryImpl(
    private val entityManager: EntityManager
) : TemplateGeneratorRepository {
    override fun <T> getTemplates(
        templateClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        rarity: Rarity,
    ): Stream<T> {
        return entityManager
            .createQuery(
                """
                        SELECT t
                        FROM ${templateClass.name} t 
                        WHERE 1=1
                        AND t.baseLevel BETWEEN :itemLevelMin AND :itemLevelMax
                        AND t.rarity >= :rarity
                        """, templateClass
            ).setParameter("itemLevelMin", itemLevelMin)
            .setParameter("itemLevelMax", itemLevelMax)
            .setParameter("rarity", rarity)
            .resultStream
    }

    override fun <T> getTemplateByLabel(
        templateClass: Class<T>,
        label: String
    ): T? {
        return entityManager
            .createQuery(
                """
                        SELECT t
                        FROM ${templateClass.name} t 
                        WHERE 1=1
                        AND LOWER(t.label) = :label
                        """, templateClass
            ).setParameter("label", label.lowercase())
            .resultStream.findAny().orElse(null)
    }
}