package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(path = ["/generate"])
class GenerationController(
    val weaponService: WeaponService,
) {
    @RequestMapping(
//        method = [RequestMethod.POST]
    )
    fun generateWeapon(

    ): GameObject<Weapon>? {
        return weaponService.generate(
        )
    }

}