package com.pessimisticit.scarcitybackend.entities.templates.equipment

import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.springframework.hateoas.server.core.Relation
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
abstract class Equipment<T : Equipment<T>> : Template<T>() {
    @Enumerated(EnumType.STRING)
    open lateinit var bindType: BindType

    open var weight: Double = 0.0 //grams

    open var itemLevel: Double = 0.0
}

