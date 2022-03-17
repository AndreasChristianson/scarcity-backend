package com.pessimisticit.scarcitybackend.entropy

import com.pessimisticit.scarcitybackend.constants.Rarity
import com.pessimisticit.scarcitybackend.constants.Tag

data class ItemGenerator<T>(
    val rarity: Rarity,
    val level: Double,
    val tags: Sequence<Tag> = sequenceOf(),
    val generator: () -> T,
)
