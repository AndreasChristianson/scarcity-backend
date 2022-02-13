package com.pessimisticit.scarcitybackend.repositories

import com.pessimisticit.scarcitybackend.entities.templates.Rarity
import com.pessimisticit.scarcitybackend.entities.templates.equipment.Equipment

interface ItemGenerationRepository<T : Equipment<T>> {

    fun getPotentials(
        requiredRarities: Collection<Rarity>,
        itemLevelMin: Double,
        itemLevelMax: Double,
        requiredTag: String?
    ): Collection<T>
}