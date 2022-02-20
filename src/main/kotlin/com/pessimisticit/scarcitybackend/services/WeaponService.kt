package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponModifierTemplate
import com.pessimisticit.scarcitybackend.objects.Weapon
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import java.util.*
import javax.transaction.Transactional

@Service
class WeaponService(
    val gameObjectGenerator: GameObjectGenerator,
    val gameEntityRepo: CrudRepository<GameEntity<*>, UUID>,
    val roller: RollerService
) {
    private val log: Logger = LoggerFactory.getLogger(WeaponService::class.java)

    @Transactional
    fun generateWeapon(
        parent: GameEntity<*>,
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): GameEntity<Weapon> {
        val weapon = gameObjectGenerator.generateGameObject(
            parent,
            WeaponTemplate::class.java,
            itemLevelMin,
            itemLevelMax,
        )
        if (roller.percent() > 90) {
            val modifier = gameObjectGenerator.generateModifier(
                weapon,
                WeaponModifierTemplate::class.java,
            )
            weapon.modifiers.add(modifier)
        }
        log.info("Generated weapon [$weapon]")
        return gameEntityRepo.save(weapon)
    }
}
