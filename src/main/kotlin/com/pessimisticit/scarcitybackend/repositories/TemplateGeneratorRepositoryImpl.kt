package com.pessimisticit.scarcitybackend.repositories

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
    ): Stream<T> {
        return entityManager
            .createQuery(
                """
                        SELECT t
                        FROM ${templateClass.name} t 
                        WHERE 1=1
                        AND t.baseLevel BETWEEN :itemLevelMin AND :itemLevelMax
                        """, templateClass
            ).setParameter("itemLevelMin", itemLevelMin)
            .setParameter("itemLevelMax", itemLevelMax)
            .resultStream
    }
}