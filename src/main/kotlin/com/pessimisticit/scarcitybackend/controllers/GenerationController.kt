package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.exceptions.NoTemplatesException
import com.pessimisticit.scarcitybackend.objects.GameObject
import com.pessimisticit.scarcitybackend.repositories.GameEntityRepository
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.hateoas.server.EntityLinks
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/generate")
class GenerationController(
    val weaponService: WeaponService,
    val gameEntityRepository: GameEntityRepository,
    val entityLinks: EntityLinks
) {

    @RequestMapping("/weapon")
    fun generateWeapon(
        @RequestParam(defaultValue = "1000") itemLevelMax: Double,
        @RequestParam(defaultValue = "0") itemLevelMin: Double,
    ): ResponseEntity<GameEntity<out GameObject>> {
        val generated = weaponService.generateWeapon(
            parent = gameEntityRepository.getUniverse(),

            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin
        )
        val link = entityLinks.linkForItemResource(GameEntity::class.java, generated.id)
        return ResponseEntity.created(link.toUri()).body(generated)
    }

    @ExceptionHandler(NoTemplatesException::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleException(e: NoTemplatesException?): String? {
        return e?.message
    }

    @ExceptionHandler(WrongObjectType::class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleWrongObjectType(e: NoTemplatesException?): String? {
        return e?.message
    }
}

