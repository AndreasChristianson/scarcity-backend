package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.rest.core.annotation.RestResource
import org.springframework.web.bind.annotation.RequestMapping
import java.util.*

@RepositoryRestResource(path = "templates")
interface TemplateRepository : CrudRepository<Template<*>, UUID> {

    fun findOneByLabelIgnoreCase(label: String): Template<*>?

    @RestResource(exported = false)
    @Query("""
        SELECT t FROM Template t 
        LEFT JOIN t._tags tags 
        WHERE t.rarity in (?1)
        AND t.itemLevel between ?2 and ?3
        AND (tags.tag = ?4 or ?4 is null)
        """)
    fun <T>getPotentials(
        requiredRarities: Collection<Rarity>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        requiredTag: String?
    ): Collection<T>
}

interface WeaponRepository : CrudRepository<Weapon, UUID> {
}

interface UniverseRepository : CrudRepository<Universe, UUID> {
}