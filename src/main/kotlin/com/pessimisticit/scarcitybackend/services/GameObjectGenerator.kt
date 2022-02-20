package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.exceptions.NoTemplatesException
import com.pessimisticit.scarcitybackend.objects.GameObject
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.springframework.stereotype.Service

@Service
class GameObjectGenerator(
    val templateRepository: TemplateGeneratorRepository,
    val timeService: TimeService,
    val roller: RollerService
) {
    fun <T : GameObject> generateGameObject(
        parent: GameEntity<*>,
        templateClass: Class<out GameObjectTemplate<T>>,
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): GameEntity<T> {
        val template = selectTemplate(
            templateClass,
            itemLevelMin,
            itemLevelMax,
        )
        val created = Created()
        val newObject = GameEntity<T>().apply {
            this.template = template
            this.changes.add(created)
            this.parent = parent
        }
        with(created) {
            this.gameTime = timeService.getGameTime
            this.parent = newObject
        }
        return newObject
    }

    fun <T : GameObject> generateModifier(
        parent: GameEntity<T>,
        templateClass: Class<out ModifierTemplate<T>>,
    ): Modifier<T> {
        val template = selectTemplate(
            templateClass,
            0.0,
            Double.POSITIVE_INFINITY,
        )
        return Modifier<T>().apply {
            this.template = template
            this.parent = parent
        }
    }

    private fun <T : Template> selectTemplate(
        templateClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): T {
        val potentials = templateRepository.getTemplates(
            templateClass,
            itemLevelMin,
            itemLevelMax,
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No [${templateClass.simpleName}]s found that fulfil the query parameters")
        }
        return roller.selectByRarity(potentials)
    }
}
