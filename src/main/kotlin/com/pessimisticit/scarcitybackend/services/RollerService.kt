package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.templates.HasRelativeRarity
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class RollerService {
    fun <T : HasRelativeRarity> selectByRarity(items: Collection<T>): T {
        val total = items.fold(0) { accumulator, element ->
            accumulator + element.rarity.relativeWeight
        }
        val index = Random.nextInt(total)
        var count = 0
        return items.dropWhile {
            count += it.rarity.relativeWeight
            count <= index
        }.first()
    }

    fun percent(): Double {
        return Random.nextDouble(0.0, 100.0)
    }
}
