package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.changes.ModifierAdded
import com.pessimisticit.scarcitybackend.entities.templates.GameObjectTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.exceptions.NoTemplateOptionsException
import com.pessimisticit.scarcitybackend.objects.GameObject
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class GameObjectGenerator(
    val templateRepository: TemplateGeneratorRepository,
    val gameEntityRepo: CrudRepository<GameEntity<*>, UUID>,
    val gameModifierRepo: CrudRepository<Modifier<*>, UUID>,
    val timeService: TimeService,
    val roller: RollerService
) {

    @Transactional
    fun <T : GameObject> generateGameObject(
        parent: GameEntity<*>,
        templateClass: Class<out GameObjectTemplate<T>>,
        itemLevelMin: Double = 0.0,
        itemLevelMax: Double = Double.POSITIVE_INFINITY,
        minRarity: Rarity = Rarity.COMMON,
        requiredTags: Collection<TagValue> = emptyList()
    ): GameEntity<T> {
        val potentials = templateRepository.getPotentialGameObjectTemplates(
            templateClass,
            itemLevelMin,
            itemLevelMax,
            minRarity,
            requiredTags,
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplateOptionsException("No ${templateClass.name} templates found that fulfil the query parameters")
        }
        val template = roller.selectByRarity(potentials)
        val created = Created()
        val newObject = GameEntity<T>().apply {
            this.template = template
            this.changes = mutableListOf(created)
            this.modifiers = mutableListOf()
            this.children = mutableListOf()
            this.parent = parent
        }
        with(created) {
            source = parent
            gameTime = timeService.getGameTime
            this.parent = newObject
        }
        return gameEntityRepo.save(newObject)
    }

    @Transactional
    fun <T : GameObject> generateModifier(
        parent: GameEntity<T>,
        templateClass: Class<out ModifierTemplate<T>>,
        itemLevelMin: Double = 0.0,
        itemLevelMax: Double = Double.POSITIVE_INFINITY,
        minRarity: Rarity = Rarity.COMMON
    ): GameEntity<T> {
        val potentials = templateRepository.getPotentialModifierTemplates(
            templateClass,
            itemLevelMin,
            itemLevelMax,
            minRarity,
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplateOptionsException("No ${templateClass.name} modifiers found that fulfil the query parameters")
        }
        val template = roller.selectByRarity(potentials)
        val newModifier = Modifier<T>().also {
            it.template = template
            it.parent = parent
        }
        parent.changes.add(
            ModifierAdded().also {
                it.source = parent
                it.gameTime = timeService.getGameTime
                it.modifier = newModifier
                it.parent = parent
            }
        )
        parent.modifiers.add(newModifier)
        gameModifierRepo.save(newModifier)
        return gameEntityRepo.save(parent)
    }
}
