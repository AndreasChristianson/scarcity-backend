package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import com.pessimisticit.scarcitybackend.repositories.TemplateRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional
import kotlin.random.Random

@Service
class TemplateGenerator(
    val templateRepository: TemplateRepository,
    val gameObjectRepo: CrudRepository<GameObject<*>, UUID>,
    val timeService: TimeService
) {
    @Transactional
    open fun <T : Equipment<T>> generate(
        parent: GameObject<*>,
        itemLevelMin: Double = 0.0,
        itemLevelMax: Double = Double.POSITIVE_INFINITY,
        minRarity: Rarity = Rarity.COMMON,
        requiredTag: TagValue? = null
    ): GameObject<T>? {
        val potentials = templateRepository.getPotentials<T>(
            Rarity.values().filter { it.relativeWeight <= minRarity.relativeWeight }.toList(),
            itemLevelMin,
            itemLevelMax,
            requiredTag?.name
        )
        if (potentials.isEmpty()) {
            return null;
        }
        val template = potentials.toList()[Random.nextInt(potentials.size)]
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

