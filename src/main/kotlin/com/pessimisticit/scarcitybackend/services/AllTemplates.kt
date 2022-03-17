package com.pessimisticit.scarcitybackend.services

import com.pessimisticit.scarcitybackend.entities.Lootable
import com.pessimisticit.scarcitybackend.entropy.ItemGenerator
import com.pessimisticit.scarcitybackend.entropy.Table
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component


@Component
class AllTemplates {
    val classLoader: ClassLoader = this.javaClass.classLoader

    private val allLootables: Sequence<Class<*>> by lazy {

        val provider = ClassPathScanningCandidateComponentProvider(false)

        provider.addIncludeFilter(AnnotationTypeFilter(Lootable::class.java))
        provider.resourceLoader = PathMatchingResourcePatternResolver(classLoader)

        val candidates = provider.findCandidateComponents("com.pessimisticit.scarcitybackend")
        candidates.asSequence()
            .map { Class.forName(it.beanClassName) }
    }

    fun <T>getTableForClass(targetClass: Class<T>):Table<T>{
        val asGenerators = allLootables
            .filter { targetClass.isAssignableFrom(it) }
            .map { clazz ->
            val annotations = clazz.annotations
            val lootableAnnotation = annotations.find { it.annotationClass == Lootable::class.java }
            requireNotNull(lootableAnnotation)
            val lootable = lootableAnnotation as Lootable
                ItemGenerator<T>(
                level = lootable.level,
                rarity = lootable.rarity,
                tags = lootable.tags.asSequence(),
                generator = { clazz.getConstructor().newInstance() as T },
            )
        }
        return Table(asGenerators)
    }
}
