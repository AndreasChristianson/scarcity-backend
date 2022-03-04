package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.templates.EquipmentTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ModifierTemplate
import com.pessimisticit.scarcitybackend.entropy.Roller
import com.pessimisticit.scarcitybackend.exceptions.NoTemplatesException
import com.pessimisticit.scarcitybackend.objects.GameObject
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.transaction.Transactional
import kotlin.reflect.KClass

abstract class GenerationService<G : GameObject, T : EquipmentTemplate<G>, MT : ModifierTemplate<G>> {
    abstract val templateInstantiator: TemplateInstantiator
    abstract val templateRepository: TemplateGeneratorRepository
    private val log: Logger = LoggerFactory.getLogger(GenerationService::class.java)

    @Transactional
    open fun generate(
        itemLevelMin: Double,
        itemLevelMax: Double,
        roller: Roller
    ): GameEntity<G> {
        val templates = getTemplates(
            itemLevelMin = itemLevelMin,
            itemLevelMax = itemLevelMax,
        )
        log.info("Generating equipment between $itemLevelMin and $itemLevelMax using ${roller::class.simpleName}")
        val template = roller.select(templates)
        log.trace("Selected ${template.label}")
        val selectedModifiers = selectModifiers(roller)
        log.trace("Selected ${selectedModifiers.size} modifiers")
        return templateInstantiator.instanciateGameEntity(
            template = template,
            modifierTemplates = selectedModifiers.distinct(),
        )
    }

    private fun selectModifiers(roller: Roller): List<ModifierTemplate<G>> {
        val allModifiers = getModifierTemplates(-1000.0, 1000.0)
        return getBeneficialModifiers(
            roller,
            allModifiers
        ) + getHarmfulModifiers(
            roller,
            allModifiers
        ) + getNeutralModifiers(
            roller,
            allModifiers
        )
    }

    private fun getBeneficialModifiers(
        roller: Roller,
        modifiers: List<ModifierTemplate<G>>
    ): List<ModifierTemplate<G>> = getModifiers(
        roller,
        roller.rollBeneficialModifierCount(),
        modifiers.filter { it.baseLevel in 0.0..1000.0 }
    )

    private fun getNeutralModifiers(
        roller: Roller,
        modifiers: List<ModifierTemplate<G>>
    ): List<ModifierTemplate<G>> =
        getModifiers(
            roller,
            roller.rollNeutralModifierCount(),
            modifiers.filter { it.baseLevel in -1.0..1.0 }
        )

    private fun getHarmfulModifiers(
        roller: Roller,
        modifiers: List<ModifierTemplate<G>>
    ): List<ModifierTemplate<G>> =
        getModifiers(
            roller,
            roller.rollHarmfulModifierCount(),
            modifiers.filter { it.baseLevel in -1000.0..0.0 }
        )

    private fun getModifiers(
        roller: Roller,
        modifierCount: Int,
        modifiers: List<ModifierTemplate<G>>
    ): List<ModifierTemplate<G>> {
        return if (modifierCount > 0) {
            (1..modifierCount)
                .map { roller.select(modifiers) }
                .onEach { log.debug("Selected modifier ${it.label}") }
                .toList()
        } else {
            listOf()
        }
    }

    @Transactional
    open fun generate(
        templateName: String,
        modifiersTemplateNames: Collection<String>,
    ): GameEntity<G> {
        log.debug("Generating a specific equipment using template [$templateName]")
        val template = getTemplateByLabel(templateName)

        val modifiers = modifiersTemplateNames
            .stream().map { getTemplateModifierByLabel(it) }
            .toList()

        return templateInstantiator.instanciateGameEntity(
            template = template,
            modifierTemplates = modifiers,
        )
    }

    private fun getTemplateByLabel(templateName: String): EquipmentTemplate<G> =
        templateRepository.getTemplateByLabel(
            templateEntityClass.java,
            templateName,
        ) ?: throw  NoTemplatesException("No template with label $templateName found")

    private fun getTemplateModifierByLabel(templateModifierName: String): ModifierTemplate<G> =
        templateRepository.getTemplateByLabel(
            modifierTemplateEntityClass.java,
            templateModifierName,
        ) ?: throw  NoTemplatesException("No modifier template with label $templateModifierName found")


    private fun getTemplates(
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): List<EquipmentTemplate<G>> {
        val potentials = templateRepository.getTemplates(
            templateEntityClass.java,
            itemLevelMin,
            itemLevelMax,
            Rarity.COMMON
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No templates found")
        }
        return potentials
    }

    private fun getModifierTemplates(
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): List<ModifierTemplate<G>> {
        val potentials = templateRepository.getTemplates(
            modifierTemplateEntityClass.java,
            itemLevelMin,
            itemLevelMax,
            Rarity.COMMON,
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No modifier templates found")
        }
        return potentials
    }

    abstract val templateEntityClass: KClass<T>

    abstract val modifierTemplateEntityClass: KClass<MT>
}

