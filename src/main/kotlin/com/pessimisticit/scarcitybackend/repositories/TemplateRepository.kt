package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

interface TemplateRepository : CrudRepository<Template<*>, UUID> {

    fun findOneByLabelIgnoreCase(label: String): Template<*>?
}
