package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.Modifier
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ModifierRepository : EnhancedRepository<Modifier, UUID> {

}
