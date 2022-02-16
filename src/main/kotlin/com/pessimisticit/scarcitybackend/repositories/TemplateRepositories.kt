package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.UniverseTemplate
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface TemplateRepositoryBase<T> : CrudRepository<T, UUID> {
    fun findOneByLabelIgnoreCase(label: String): T?
}

interface TemplateRepository : TemplateRepositoryBase<Template> {
}

interface WeaponTemplateRepository : TemplateRepositoryBase<WeaponTemplate> {
}

interface UniverseTemplateRepository : TemplateRepositoryBase<UniverseTemplate> {
}