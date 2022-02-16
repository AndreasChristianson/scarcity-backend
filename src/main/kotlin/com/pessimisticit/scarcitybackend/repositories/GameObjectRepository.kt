package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.UniverseTemplate
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface GameObjectRepository : CrudRepository<GameObject<*>, UUID> {

    @Query("""
        select u from GameObject u
        where u.id = '00000000-0000-0000-0000-000000000000'
        """)
    fun getUniverse(): GameObject<UniverseTemplate>
}
