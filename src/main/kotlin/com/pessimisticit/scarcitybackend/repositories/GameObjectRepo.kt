package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import org.springframework.data.repository.CrudRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(path = "weapon")
interface GameObjectRepo: CrudRepository<Weapon<*>, UUID> {
//    fun findAll ():Collection<Weapon<*>>
}