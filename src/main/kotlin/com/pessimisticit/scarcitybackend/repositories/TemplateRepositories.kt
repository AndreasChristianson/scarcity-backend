package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(path = "templates")
interface TemplateRepository : CrudRepository<Template<*>, UUID>, TemplateGeneratorRepository {
    fun findOneByLabelIgnoreCase(label: String): Template<*>?
}

interface WeaponTemplateRepository : CrudRepository<Weapon, UUID> {
}

interface UniverseTemplateRepository : CrudRepository<Universe, UUID> {
}