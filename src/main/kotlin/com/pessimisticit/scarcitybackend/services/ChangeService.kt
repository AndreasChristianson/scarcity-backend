package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.repositories.ChangeRepository
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ChangeService(
    private val timeService: TimeService,
    private val changeRepository: ChangeRepository,

    ) {
    private val log: Logger = LoggerFactory.getLogger(ChangeService::class.java)

    fun created(
        gameObject: GameObject
    ){
        val created = changeRepository.save(Change().apply {
            this.gameObject = gameObject
            gameTime = timeService.getGameTime
            message = "Created."
        })
        log.trace(created.toString())
    }

    fun modifierAdded(gameObject: GameObject, modifier: Modifier) {
        val modified = changeRepository.save(Change().apply {
            this.gameObject = gameObject
            gameTime = timeService.getGameTime
            message = "Modifier [$modifier] added."
        })
        log.trace(modified.toString())
    }
}
