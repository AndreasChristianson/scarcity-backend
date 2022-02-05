package com.pessimisticit.scarcitybackend.entities.templates.equipment

import com.pessimisticit.scarcitybackend.entities.templates.Template
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
abstract class Equipment<T : Equipment<T>> : Template<T>() {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    open lateinit var bindType: BindType

    @Column(nullable = false)
    open var weight: Double = 0.0

    @Column(nullable = false)
    open var volume: Double = 0.0
}

enum class BindType {
    BIND_ON_PICKUP,
    BIND_ON_USE,
    NEVER_BINDS,
}
