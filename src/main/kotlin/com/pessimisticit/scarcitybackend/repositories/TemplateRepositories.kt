package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RepositoryRestResource(path = "templates")
interface TemplateRepository : CrudRepository<Template<*>, UUID> {

    fun findOneByLabelIgnoreCase(label: String): Template<*>?

    @RestResource(exported = false)
    @Query(
        """
        SELECT distinct t
        FROM Template t
        JOIN t._tags tag
        WHERE t.rarity IN (?3)
        AND t.itemLevel BETWEEN ?1 AND ?2
        AND (tag.tag in(?4))
        """
    )
    fun <T> getPotentials(
        itemLevelMin: Double,
        itemLevelMax: Double,
        requiredRarities: Collection<Rarity>,
        requiredTag: Collection<TagValue>,
    ): Collection<T>

    @Query(
        """
        SELECT distinct t
        FROM Template t
        JOIN t._tags tag
        WHERE t.rarity IN (?3)
        AND t.itemLevel BETWEEN ?1 AND ?2
        """
    )
    fun <T> getPotentials(
        itemLevelMin: Double,
        itemLevelMax: Double,
        requiredRarities: Collection<Rarity>,
    ): Collection<T>
}

interface WeaponTemplateRepository : CrudRepository<Weapon, UUID> {
}

interface UniverseTemplateRepository : CrudRepository<Universe, UUID> {
}