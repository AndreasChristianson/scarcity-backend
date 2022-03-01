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

abstract class GenerationService<G : GameObject, T: EquipmentTemplate<G>, MT:ModifierTemplate<G>> {
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
            rarity = Rarity.COMMON
        )
        log.info("Generating equipment between $itemLevelMin and $itemLevelMax using ${roller::class.simpleName}")
        val template = roller.select(templates)
        log.trace("Selected ${template.label}")
        val selectedModifiers =
            getBeneficialModifiers(roller) + getHarmfulModifiers(roller) + getNeutralModifiers(roller)
        return templateInstantiator.generateGameObject(
            template = template,
            modifierTemplates = selectedModifiers.distinct(),
        )
    }

    private fun getBeneficialModifiers(roller: Roller): List<ModifierTemplate<G>> = getModifiers(
        roller,
        roller::rollBeneficialModifierCount,
        this::getBeneficialModifierTemplates,
    )

    private fun getNeutralModifiers(roller: Roller): List<ModifierTemplate<G>> = getModifiers(
        roller,
        roller::rollNeutralModifierCount,
        this::getNeutralModifierTemplates,
    )

    private fun getHarmfulModifiers(roller: Roller): List<ModifierTemplate<G>> = getModifiers(
        roller,
        roller::rollHarmfulModifierCount,
        this::getHarmfulModifierTemplates,
    )

    private fun getModifiers(
        roller: Roller,
        modifierCountFunction: () -> Int,
        modifierSelectorFunction: () -> List<ModifierTemplate<G>>
    ): List<ModifierTemplate<G>> {
        val modifierCount = modifierCountFunction()
        return if (modifierCount > 0) {
            val modifiers = modifierSelectorFunction()
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

        return templateInstantiator.generateGameObject(
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
        rarity: Rarity,
    ): List<EquipmentTemplate<G>> {
        val potentials = templateRepository.getTemplates(
            templateEntityClass.java,
            itemLevelMin,
            itemLevelMax,
            rarity
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No templates found")
        }
        return potentials
    }

    private fun getBeneficialModifierTemplates() = getModifierTemplates(
        0.0,
        1000.0,
        Rarity.COMMON
    )

    private fun getNeutralModifierTemplates() = getModifierTemplates(
        -1.0,
        1.0,
        Rarity.COMMON
    )

    private fun getHarmfulModifierTemplates() = getModifierTemplates(
        -1000.0,
        0.0,
        Rarity.COMMON
    )

    private fun getModifierTemplates(
        itemLevelMin: Double,
        itemLevelMax: Double,
        rarity: Rarity
    ): List<ModifierTemplate<G>> {
        val potentials = templateRepository.getTemplates(
            modifierTemplateEntityClass.java,
            itemLevelMin,
            itemLevelMax,
            rarity,
        ).toList()
        if (potentials.isEmpty()) {
            throw NoTemplatesException("No modifier templates found")
        }
        return potentials
    }

    abstract val templateEntityClass: KClass<T>

    abstract val modifierTemplateEntityClass: KClass<MT>
}
