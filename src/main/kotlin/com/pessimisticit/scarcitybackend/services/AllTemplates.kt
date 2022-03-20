package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entropy.ItemGenerator
import com.pessimisticit.scarcitybackend.entropy.Lootable
import com.pessimisticit.scarcitybackend.entropy.Table
import org.springframework.stereotype.Component


@Component
class AllTemplates : AnnotationCollector<Lootable>() {
    val classLoader: ClassLoader = this.javaClass.classLoader

    fun <T> getTableForClass(targetClass: Class<T>): Table<T> {
        val asGenerators = allMatches
            .filter { targetClass.isAssignableFrom(it.second) }
            .map {
                ItemGenerator<T>(
                    level = it.first.level,
                    rarity = it.first.rarity,
                    tags = it.first.tags.asSequence(),
                    generator = { it.second.getConstructor().newInstance() as T },
                )
            }
        return Table(asGenerators)
    }

    override val annotationTarget: Class<out Lootable>
        get() = Lootable::class.java
}
