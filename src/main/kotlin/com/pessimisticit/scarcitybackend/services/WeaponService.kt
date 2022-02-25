package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponModifierTemplate
import com.pessimisticit.scarcitybackend.entropy.Roller
import com.pessimisticit.scarcitybackend.exceptions.NoTemplatesException
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.Transactional
import kotlin.streams.toList

@Service
class WeaponService(
    val templateInstantiator: TemplateInstantiator,
    val templateRepository: TemplateGeneratorRepository,
) {
    private val log: Logger = LoggerFactory.getLogger(WeaponService::class.java)

    @Transactional
    fun generateWeapon(
        itemLevelMin: Double,
        itemLevelMax: Double,
        roller: Roller
    ): GameEntity<Weapon> {
        val templates = getWeaponTemplates(
            itemLevelMin = itemLevelMin,
            itemLevelMax = itemLevelMax
        )
        log.info("Generating weapon between $itemLevelMin and $itemLevelMax using ${roller::class.simpleName}")
        val template = roller.select(templates)
        log.trace("Selected ${template.label}")
        val modifierCount = roller.getModifierCount()
        log.trace("Rolled $modifierCount modifiers")
        val selectedModifiers = if (modifierCount > 0) {
            val modifiers = getWeaponModifierTemplates()
            (1..modifierCount)
                .map { roller.select(modifiers) }
                .onEach { log.debug("Selected modifier ${it.label}") }
                .toList()
        } else {
            listOf()
        }

        return templateInstantiator.generateGameObject(
            template = template,
            modifierTemplates = selectedModifiers,
        )
    }
    @Transactional
    fun generateWeapon(
        weaponTemplateName: String,
        modifiersTemplateNames: Collection<String>,
    ): GameEntity<Weapon> {
        log.debug("Generating a specific weapon using template [$weaponTemplateName]")
        val template = getWeaponTemplateByLabel(weaponTemplateName)

        val modifiers= modifiersTemplateNames
            .stream().map { getWeaponTemplateModifierByLabel(it) }
            .toList()

        return templateInstantiator.generateGameObject(
            template = template,
            modifierTemplates = modifiers,
        )
    }

    private fun getWeaponTemplateByLabel(weaponTemplateName: String): WeaponTemplate =
        templateRepository.getTemplateByLabel(
            WeaponTemplate::class.java,
            weaponTemplateName,
        ) ?: throw  NoTemplatesException("No weapon with label $weaponTemplateName found")

    private fun getWeaponTemplateModifierByLabel(weaponTemplateModifierName: String): WeaponModifierTemplate =
        templateRepository.getTemplateByLabel(
            WeaponModifierTemplate::class.java,
            weaponTemplateModifierName,
        ) ?: throw  NoTemplatesException("No weapon modifier with label $weaponTemplateModifierName found")


    private fun getWeaponTemplates(
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): List<WeaponTemplate> {
        val potentials = templateRepository.getTemplates(
            WeaponTemplate::class.java,
            itemLevelMin,
            itemLevelMax,
            Rarity.COMMON
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No weapon templates found")
        }
        return potentials
    }

    private fun getWeaponModifierTemplates(
    ): List<WeaponModifierTemplate> {
        val potentials = templateRepository.getTemplates(
            WeaponModifierTemplate::class.java,
            0.0,
            1000.0,
            Rarity.COMMON
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No weapon modifier templates found")
        }
        return potentials
    }
}

