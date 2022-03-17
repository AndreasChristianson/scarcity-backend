package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.repositories.GameEntityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TemplateInstantiator(
    val timeService: TimeService,
    val gameEntityRepo: GameEntityRepository,

    ) {
    private val log: Logger = LoggerFactory.getLogger(TemplateInstantiator::class.java)

    fun <T : GameEntity> instanciateGameEntity(
        parent: GameEntity = gameEntityRepo.getUniverse(),
        unsaved: T,
    ): T {
        val created = Created()
        val newObject = unsaved.apply {
            this.changes.add(created)
            this.parent = parent
        }
        with(created) {
            this.gameTime = timeService.getGameTime
            this.parent = newObject
        }
        log.trace("saving $newObject")
        val saved = gameEntityRepo.save(newObject)
        log.debug("saved $saved")
        return saved
    }
}
