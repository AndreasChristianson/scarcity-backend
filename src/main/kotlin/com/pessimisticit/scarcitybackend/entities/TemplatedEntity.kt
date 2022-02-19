package com.pessimisticit.scarcitybackend.entities

import com.pessimisticit.scarcitybackend.entities.templates.Template
import javax.persistence.ManyToOne
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class TemplatedEntity<T : Template> : AbstractJpaPersistable() {
    @ManyToOne(optional = false, targetEntity = Template::class)
    lateinit var template: T

    override fun toString(): String {
        return "${template.label}($id)"
    }
}