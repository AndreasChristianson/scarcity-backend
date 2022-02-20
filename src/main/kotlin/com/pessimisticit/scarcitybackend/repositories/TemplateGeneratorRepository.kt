package com.pessimisticit.scarcitybackend.repositories

import java.util.stream.Stream

interface TemplateGeneratorRepository {
    fun <T> getTemplates(
        templateClass: Class<T>,
        itemLevelMin: Double,
        itemLevelMax: Double,
    ): Stream<T>
}
