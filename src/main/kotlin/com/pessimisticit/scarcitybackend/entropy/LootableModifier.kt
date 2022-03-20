package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.constants.ModifierType
import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag
import java.lang.annotation.Inherited
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Inherited
annotation class LootableModifier(
    val rarity: Rarity,
    val modifierType: ModifierType,
    val modifierTargets: Array<KClass<*>>,
    val tags: Array<Tag> = [],
    )
