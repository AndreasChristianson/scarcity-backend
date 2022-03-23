package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.constants.Tag
import com.pessimisticit.scarcitybackend.controllers.hateoas.GameObjectAssembler
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.entities.equipment.Equipment
import com.pessimisticit.scarcitybackend.entities.equipment.armors.Armor
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.melee.MeleeWeapon
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.ranged.RangedWeapon
import com.pessimisticit.scarcitybackend.entropy.GenerationProfile
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod.GET
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("game-object/generate-example")
class GenerationController(
    val weaponService: WeaponService,
    private val gameObjectAssembler: GameObjectAssembler
) {
    enum class GenerationTypes(val clazz: Class<out GameObject>) {
        WEAPON(Weapon::class.java),
        MELEE_WEAPON(MeleeWeapon::class.java),
        RANGED_WEAPON(RangedWeapon::class.java),
        ARMOR(Armor::class.java),
        EQUIPMENT(Equipment::class.java),
    }

    @RequestMapping(method = [GET])
    fun generate(
        @RequestParam(defaultValue = "EQUIPMENT") type: GenerationTypes,
        @RequestParam(defaultValue = "COMMON") profile: GenerationProfile,
        @RequestParam(defaultValue = "1000.0") itemLevelMax: Double,
        @RequestParam(defaultValue = "0.0") itemLevelMin: Double,
        @RequestParam(required = false) requiredTag: Tag?,
    ): ResponseEntity<*> {
        val generated = weaponService.generate(
            targetClass = type.clazz,
            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin,
            requiredTag = requiredTag,
            profile = profile,
        )

        return created(generated)
    }

    private fun created(generated: GameObject?): ResponseEntity<*> =
        if (generated == null)
            ResponseEntity.badRequest().body("No templates found that meet your request specification.")
        else
            ResponseEntity.created(
                linkTo(GameObjectController::class.java).slash(generated.id).toUri()
            ).body(gameObjectAssembler.toModel(generated))
}
