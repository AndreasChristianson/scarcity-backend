package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.equipment.weapons.Weapon
import com.pessimisticit.scarcitybackend.services.WeaponService
import org.springframework.data.rest.webmvc.BasePathAwareController
import org.springframework.hateoas.server.EntityLinks
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam


@BasePathAwareController
class WeaponGenerationController(
    val weaponService: WeaponService,
    val entityLinks: EntityLinks
) {

    @RequestMapping("/generate/weapon")
    fun generateCommonWeapon(
        @RequestParam(defaultValue = "1000.0") itemLevelMax: Double,
        @RequestParam(defaultValue = "0.0") itemLevelMin: Double,
        @RequestParam(defaultValue = "0.0") modifierQualitySkew: Double,
        @RequestParam(defaultValue = "0.0") modifierQuantitySkew: Double,
        @RequestParam(defaultValue = "0.0") raritySkew: Double,
        @RequestParam(required = false) name: String?,
    ): ResponseEntity<*> {
        val generated = weaponService.generate(
            targetClass = Weapon::class.java,
            itemLevelMax = itemLevelMax,
            itemLevelMin = itemLevelMin,
//            modifierQualitySkew = modifierQualitySkew,
//            modifierQuantitySkew = modifierQuantitySkew,
            raritySkew = raritySkew,
        ) ?: return ResponseEntity.badRequest().body("No options matched your query.")

        val link = entityLinks.linkForItemResource(GameEntity::class.java, generated.id)
        return ResponseEntity.created(link.toUri()).body(generated)
    }

//    @ExceptionHandler(NoTemplatesException::class)
//    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    fun handleException(e: NoTemplatesException?): String? {
//        return e?.message
//    }
}
