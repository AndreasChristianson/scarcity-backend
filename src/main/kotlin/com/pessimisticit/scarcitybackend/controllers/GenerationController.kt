package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponModifierTemplate
import com.pessimisticit.scarcitybackend.exceptions.NoTemplateOptionsException
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.repositories.GameEntityRepository
import com.pessimisticit.scarcitybackend.services.GameObjectGenerator
import org.springframework.hateoas.server.EntityLinks
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(path = ["/generate"])
class GenerationController(
    val generator: GameObjectGenerator,
    val gameEntityRepository: GameEntityRepository,
    val entityLinks: EntityLinks
) {
    @RequestMapping()
    fun generateWeapon(

    ): ResponseEntity<GameEntity<Weapon>> {
        val weapon = generator.generateModifier(
            parent = generator.generateGameObject(
                gameEntityRepository.getUniverse(),
                WeaponTemplate::class.java,
            ),
            templateClass = WeaponModifierTemplate::class.java,
        )
        val link = entityLinks.linkForItemResource(GameEntity::class.java, weapon.id)
        return ResponseEntity.created(link.toUri()).body(weapon)
    }

    @ExceptionHandler(NoTemplateOptionsException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(e: NoTemplateOptionsException?): String? {
        return e?.message
    }
}

