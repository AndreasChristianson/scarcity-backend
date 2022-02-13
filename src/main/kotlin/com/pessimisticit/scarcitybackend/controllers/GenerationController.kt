package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.templates.Universe
import com.pessimisticit.scarcitybackend.entities.templates.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import com.pessimisticit.scarcitybackend.repositories.UniverseTemplateRepository
import com.pessimisticit.scarcitybackend.services.TemplateGenerator
import org.springframework.hateoas.server.EntityLinks
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/generate"])
class GenerationController(
    val generator: TemplateGenerator,
    val gameObjectRepository: GameObjectRepository,
    val entityLinks:EntityLinks
) {
    @RequestMapping(
//        method = [RequestMethod.POST]
    )
    fun generateWeapon(

    ): ResponseEntity<GameObject<Weapon>> {
        val result = generator.generate<Weapon>(
            gameObjectRepository.getUniverse()
        ) ?: return ResponseEntity.badRequest().build()
        val link = entityLinks.linkForItemResource(GameObject::class.java, result.id!!)
        return ResponseEntity.created(link.toUri()).body(result)
    }

}