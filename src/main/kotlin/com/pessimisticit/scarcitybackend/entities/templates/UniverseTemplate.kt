package com.pessimisticit.scarcitybackend.entities.templates


import com.pessimisticit.scarcitybackend.objects.Universe
import javax.persistence.Entity

@Entity
class UniverseTemplate : GameObjectTemplate<Universe>() {
    override fun generateInstance(): Universe {
        return Universe().also {
            it.flavor = flavor
            it.icon = icon
            it.label = label
            it.description = description
        }
    }

}
