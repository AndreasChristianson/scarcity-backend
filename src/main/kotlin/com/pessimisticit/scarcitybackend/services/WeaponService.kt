package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.templates.WeaponTemplate
import com.pessimisticit.scarcitybackend.entities.templates.modifiers.WeaponModifierTemplate
import com.pessimisticit.scarcitybackend.objects.Weapon
import com.pessimisticit.scarcitybackend.repositories.TemplateGeneratorRepository
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class WeaponService(
    override val templateInstantiator: TemplateInstantiator,
    override val templateRepository: TemplateGeneratorRepository,
) : GenerationService<Weapon, WeaponTemplate, WeaponModifierTemplate>() {
    override val templateEntityClass: KClass<WeaponTemplate>
        get() = WeaponTemplate::class

    override val modifierTemplateEntityClass: KClass<WeaponModifierTemplate>
        get() = WeaponModifierTemplate::class
}

