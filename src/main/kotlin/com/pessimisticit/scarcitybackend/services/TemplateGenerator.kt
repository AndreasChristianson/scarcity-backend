package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.changes.Created
import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.TagValue
import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment
import com.pessimisticit.scarcitybackend.repositories.ItemGenerationRepository
import org.springframework.data.repository.CrudRepository
import java.util.*
import javax.transaction.Transactional
import kotlin.random.Random


abstract class TemplateGenerator<T:Equipment<T>>  {

    abstract val itemGenerationRepository: ItemGenerationRepository<T>
    abstract val gameObjectRepo: CrudRepository<GameObject<*>, UUID>
    abstract val timeService: TimeService

    @Transactional
    open fun generate(
        parent: GameObject<*>? = null,
        itemLevelMin: Double = 0.0,
        itemLevelMax: Double = Double.POSITIVE_INFINITY,
        minRarity: Rarity = Rarity.COMMON,
        requiredTag: TagValue? = null
    ): GameObject<T>? {
        val potentials = itemGenerationRepository.getPotentials(
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
        with(created){
            source = parent
            gameTime = timeService.getGameTime
            this.parent = newObject
        }
        return gameObjectRepo.save(newObject)
    }

}

