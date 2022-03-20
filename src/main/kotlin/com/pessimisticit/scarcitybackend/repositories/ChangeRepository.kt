package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.changes.Change
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChangeRepository : JpaRepository<Change, UUID> {
    @Query(
        """
        select u from Change u
        where u.gameObject.id = :id
        """
    )
    fun findChanges(id: UUID, pageable: Pageable): Page<Change>

}
