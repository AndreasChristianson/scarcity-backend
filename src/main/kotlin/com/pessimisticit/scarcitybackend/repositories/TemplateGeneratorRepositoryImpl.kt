package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.objects.GameObject
import org.springframework.stereotype.Repository
import java.util.stream.Stream
import javax.persistence.EntityManager


@Repository
class TemplateGeneratorRepositoryImpl(
    private val entityManager: EntityManager
) : TemplateGeneratorRepository {
    override fun <T : GameObject> getPotentialGameObjectTemplates(
        templateClass: Class<out GameObjectTemplate<T>>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity,
        requiredTags: Collection<TagValue>
    ): Stream<out GameObjectTemplate<T>> {
        val validRarities = computeValidRarities(minRarity)
        when {
            requiredTags.isEmpty() ->
                return getPotentialTemplates(
                    templateClass,
                    itemLevelMin,
                    itemLevelMax,
                    validRarities,
                )
            else ->
                return entityManager
                    .createQuery(
                        """
                        SELECT distinct t
                        FROM ${templateClass.name} t
                        JOIN t._tags tag
                        WHERE t.rarity IN (:validRarities)
                        AND t.baseLevel :itemLevelMin AND :itemLevelMax
                        AND (tag.tag in (:requiredTags))
                        """, templateClass
                    )
                    .setParameter("requiredTags", requiredTags)
                    .setParameter("itemLevelMin", itemLevelMin)
                    .setParameter("itemLevelMax", itemLevelMax)
                    .setParameter("validRarities", validRarities)
                    .resultStream

        }
    }

    private fun computeValidRarities(minRarity: Rarity) = Rarity.values()
        .filter { it.relativeWeight <= minRarity.relativeWeight }
        .toList()

    private fun <T> getPotentialTemplates(
        templateClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        validRarities: Collection<Rarity>
    ): Stream<T> {
        return entityManager
            .createQuery(
                """
                        SELECT t
                        FROM ${templateClass.name} t 
                        WHERE t.rarity IN (:validRarities)
                        AND t.baseLevel BETWEEN :itemLevelMin AND :itemLevelMax
                        """, templateClass
            ).setParameter("itemLevelMin", itemLevelMin)
            .setParameter("itemLevelMax", itemLevelMax)
            .setParameter("validRarities", validRarities)
            .resultStream
    }

    override fun <T : GameObject> getPotentialModifierTemplates(
        templateClass: Class<out ModifierTemplate<T>>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        minRarity: Rarity
    ): Stream<out ModifierTemplate<T>> {
        return getPotentialTemplates(
            templateClass,
            itemLevelMin,
            itemLevelMax,
            computeValidRarities(minRarity)
        )
    }
}