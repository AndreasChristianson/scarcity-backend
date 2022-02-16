package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.UniverseTemplate
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.WeaponTemplate
import org.springframework.data.repository.CrudRepository
import java.util.*

//@RepositoryRestResource(path = "templates")
interface TemplateRepository : CrudRepository<Template, UUID>, TemplateGeneratorRepository {
    fun findOneByLabelIgnoreCase(label: String): Template?
}

interface WeaponTemplateRepository : CrudRepository<WeaponTemplate, UUID> {
}

interface UniverseTemplateRepository : CrudRepository<UniverseTemplate, UUID> {
}