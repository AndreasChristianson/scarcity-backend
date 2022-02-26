package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.objects.GameObject
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

    fun <T : GameObject> generateGameObject(
        parent: GameEntity<*> = gameEntityRepo.getUniverse(),
        template: GameObjectTemplate<T>,
        modifierTemplates: Collection<ModifierTemplate<T>>,
    ): GameEntity<T> {
        val created = Created()
        val newObject = GameEntity<T>().apply {
            this.template = template
            this.changes.add(created)
            this.parent = parent
            this.modifiers = modifierTemplates
                .stream().map { modifierTemplate ->
                    Modifier<T>().also {
                        it.parent = this
                        it.template = modifierTemplate
                    }
                }.toList()
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
