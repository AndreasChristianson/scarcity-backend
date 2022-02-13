package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.services.TemplateGenerator
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/generate"])
class GenerationController(
    val generator: TemplateGenerator,
) {
    @RequestMapping(
//        method = [RequestMethod.POST]

    )
    fun generateWeapon(

    ): GameObject<Weapon>? {
        return generator.generate(
        )
    }

}