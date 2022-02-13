package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.repositories.ItemGenerationRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import java.util.*


@Component
open class WeaponService(
    override val itemGenerationRepository: ItemGenerationRepository<Weapon>,
    override val gameObjectRepo: CrudRepository<GameObject<*>, UUID>,
    override val timeService: TimeService
) : TemplateGenerator<Weapon>() {

}

