package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.templates.ArmorTemplate
import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.ArmorModifierTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponModifierTemplate
import com.pessimisticit.scarcitybackend.objects.Armor
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class ArmorService(
    override val templateInstantiator: TemplateInstantiator,
    override val templateRepository: TemplateGeneratorRepository,
) : GenerationService<Armor, ArmorTemplate, ArmorModifierTemplate>() {
    override val templateEntityClass: KClass<ArmorTemplate>
        get() = ArmorTemplate::class

    override val modifierTemplateEntityClass: KClass<ArmorModifierTemplate>
        get() = ArmorModifierTemplate::class
}

