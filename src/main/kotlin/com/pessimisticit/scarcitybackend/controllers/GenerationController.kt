package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entropy.CommonRoller
import com.pessimisticit.scarcitybackend.entropy.EliteRoller
import com.pessimisticit.scarcitybackend.exceptions.NoTemplatesException
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.hateoas.server.EntityLinks
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@BasePathAwareController
class GenerationController(
    val weaponService: WeaponService,
    val entityLinks: EntityLinks
) {

    @RequestMapping("/generate/common-weapon")
    fun generateCommonWeapon(
        @RequestParam(defaultValue = "1000") itemLevelMax: Double,
        @RequestParam(defaultValue = "0") itemLevelMin: Double,
    ): ResponseEntity<GameEntity<Weapon>> {
        val generated = weaponService.generateWeapon(
            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin,
            roller = CommonRoller
        )
        val link = entityLinks.linkForItemResource(GameEntity::class.java, generated.id)
        return ResponseEntity.created(link.toUri()).body(generated)
    }

    @RequestMapping("/generate/elite-weapon")
    fun generateEliteWeapon(
        @RequestParam(defaultValue = "1000") itemLevelMax: Double,
        @RequestParam(defaultValue = "0") itemLevelMin: Double,
    ): ResponseEntity<GameEntity<Weapon>> {
        val generated = weaponService.generateWeapon(
            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin,
            roller = EliteRoller
        )
        val link = entityLinks.linkForItemResource(GameEntity::class.java, generated.id)
        return ResponseEntity.created(link.toUri()).body(generated)
    }

    @RequestMapping("/generate/weapon")
    fun generateSpecificWeapon(
        @RequestParam templateName: String,
        @RequestParam(name = "modifier",defaultValue = "") modifiers: Collection<String>,
    ): ResponseEntity<GameEntity<Weapon>> {
        val generated = weaponService.generateWeapon(
            weaponTemplateName = templateName,
            modifiersTemplateNames = modifiers
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
}
