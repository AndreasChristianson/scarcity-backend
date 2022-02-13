package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.rest.core.annotation.RestResource
import java.util.*

@RestResource(exported = false)
interface WeaponGenerationRepository : ItemGenerationRepository<Weapon>, JpaRepository<Weapon, UUID> {
    @Query("""
        SELECT t FROM Template t 
        LEFT JOIN t._tags tags 
        WHERE t.rarity in (?1)
        AND t.itemLevel between ?2 and ?3
        AND (tags.tag = ?4 or ?4 is null)
        """)
    override fun getPotentials(
        requiredRarities: Collection<Rarity>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        requiredTag: String?
    ): Collection<Weapon>
}