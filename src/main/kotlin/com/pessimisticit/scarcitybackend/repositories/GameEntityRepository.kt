package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.objects.Universe
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface GameEntityRepository : PagingAndSortingRepository<GameEntity<*>, UUID> {

    @Query(
        """
        select u from GameEntity u
        where u.id = '00000000-0000-0000-0000-000000000000'
        """
    )
    fun getUniverse(): GameEntity<Universe>
}
