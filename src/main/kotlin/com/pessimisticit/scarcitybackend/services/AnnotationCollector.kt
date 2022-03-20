package com.pessimisticit.scarcitybackend.services

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.stereotype.Component


@Component
abstract class AnnotationCollector<T : Annotation> {
    private val classLoader: ClassLoader = this.javaClass.classLoader
    abstract val annotationTarget: Class<out T>

    protected val allMatches: Sequence<Pair<T, Class<*>>> by lazy {
        val provider = ClassPathScanningCandidateComponentProvider(false)
        provider.addIncludeFilter(AnnotationTypeFilter(annotationTarget))
        provider.resourceLoader = PathMatchingResourcePatternResolver(classLoader)
        val candidates = provider.findCandidateComponents("com.pessimisticit.scarcitybackend")

        candidates.asSequence()
            .map { Class.forName(it.beanClassName) }
            .map { getAnnotation(it) to it }
    }

    private fun getAnnotation(clazz: Class<*>): T {
        val potentialAnnotation = clazz.annotations.find { it.annotationClass.java == annotationTarget }
        requireNotNull(potentialAnnotation)
        return potentialAnnotation as T
    }
}
