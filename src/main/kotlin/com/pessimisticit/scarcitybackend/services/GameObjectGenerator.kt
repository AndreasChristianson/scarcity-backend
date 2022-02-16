package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.Template
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class GameObjectGenerator(
    val templateRepository: TemplateGeneratorRepository,
    val gameObjectRepo: CrudRepository<GameObject<*>, UUID>,
    val timeService: TimeService,
    val roller: RollerService
) {
    @Transactional
    open fun <T : Template> generate(
        entityClass: Class<T>,
        parent: GameObject<*>,
        itemLevelMin: Double = 0.0,
        itemLevelMax: Double = Double.POSITIVE_INFINITY,
        minRarity: Rarity = Rarity.COMMON,
        requiredTags: Collection<TagValue> = emptyList()
    ): GameObject<T>? {
        val potentials = templateRepository.getPotentialTemplates(
            entityClass,
            itemLevelMin,
            itemLevelMax,
            minRarity,
            requiredTags,
        ).toList()
        if (potentials.isEmpty()) {
            return null;
        }
        val template = roller.selectByRarity(potentials)
        val created = Created()
        val newObject = GameObject<T>().apply {
            this.template = template
            this.changes = listOf(created)
            this.modifiers = emptyList()
            this.children = emptyList()
            this.parent = parent
        }
        with(created) {
            source = parent
            gameTime = timeService.getGameTime
            this.parent = newObject
        }
        return gameObjectRepo.save(newObject)
    }

}

