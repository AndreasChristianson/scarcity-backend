package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.Modifier
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource
interface GameModifierRepository : CrudRepository<Modifier<*>, UUID>
