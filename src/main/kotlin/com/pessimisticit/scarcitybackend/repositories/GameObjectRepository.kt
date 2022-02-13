package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import java.util.*

interface GameObjectRepository : CrudRepository<GameObject<*>, UUID> {

}
