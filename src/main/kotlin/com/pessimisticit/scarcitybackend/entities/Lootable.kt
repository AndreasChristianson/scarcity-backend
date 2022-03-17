package com.pessimisticit.scarcitybackend.entities

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag

@Retention(AnnotationRetention.RUNTIME)
annotation class Lootable(
    val tags: Array<Tag> = [],
    val rarity: Rarity,
    val level: Double,
)