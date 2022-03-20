package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.Modifier
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import com.pessimisticit.scarcitybackend.repositories.ModifierRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class GameObjectService(
    val modifierRepository: ModifierRepository,
    val gameObjectRepository: GameObjectRepository,
    val changeService: ChangeService

) {
    private val log: Logger = LoggerFactory.getLogger(GameObjectService::class.java)

    fun <T : GameObject> createGameObject(
        parent: GameObject = gameObjectRepository.getUniverse(),
        gameObject: T,
    ): T {
        gameObject.parent = parent
        val saved = gameObjectRepository.save(gameObject)
        changeService.created(saved)

        return saved
    }

    fun <T : GameObject> addModifiers(
        gameObject: T,
        modifiers: List<Modifier>
    ): T {
        modifiers.forEach { addModifier(gameObject, it) }
        return gameObject
    }

    fun <T : GameObject> addModifier(
        gameObject: T,
        modifier: Modifier
    ): T {
        modifier.parent = gameObject
        gameObject.modifiers.add(modifier)
        modifierRepository.save(modifier)
        changeService.modifierAdded(gameObject, modifier)
        return gameObject
    }
}
