package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.GameObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GameObjectRepository : EnhancedRepository<GameObject, UUID> {

    @Query(
        """
        select u from GameObject u
        where u.id = '00000000-0000-0000-0000-000000000000'
        """
    )
    fun getUniverse(): GameObject

    @Query(
        """
        select u from GameObject u
        where u.parent.id = :id
        """
    )
    fun findChildren(id: UUID, pageable: Pageable): Page<GameObject>

    @Query(
        """
        select u.parent from GameObject u
        where u.id = :id
        """
    )
    fun findParent(id: UUID): Optional<GameObject>
}
