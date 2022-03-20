package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.controllers.hateos.GameObjectAssembler
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("game-object/generate-example")
class GenerationController(
    val weaponService: WeaponService,
    private val gameObjectAssembler: GameObjectAssembler
) {

    @RequestMapping(method = [RequestMethod.GET], path = ["/weapon"])
    fun generateWeapon(
        @RequestParam(defaultValue = "1000.0") itemLevelMax: Double,
        @RequestParam(defaultValue = "0.0") itemLevelMin: Double,
        @RequestParam(defaultValue = "") modifierQualitySkew: Double?,
        @RequestParam(defaultValue = "") modifierQuantitySkew: Double?,
        @RequestParam(defaultValue = "") raritySkew: Double?,
        @RequestParam(required = false) name: String?,
    ): ResponseEntity<*> {
        val generated = weaponService.generate(
            targetClass = Weapon::class.java,
            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin,
            modifierQualitySkew = modifierQualitySkew,
            modifierQuantitySkew = modifierQuantitySkew,
            raritySkew = raritySkew,
        ) ?: return ResponseEntity.badRequest().body("No templates found that meet your request specification.")

        return ResponseEntity.created(
            WebMvcLinkBuilder.linkTo(GameObjectController::class.java).slash(generated.id).toUri()
        ).body(gameObjectAssembler.toModel(generated))
    }
}
