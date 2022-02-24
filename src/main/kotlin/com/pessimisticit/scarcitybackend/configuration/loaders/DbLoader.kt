package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.constants.TagValue
import com.pessimisticit.scarcitybackend.entities.GameEntity
import com.pessimisticit.scarcitybackend.entities.Tag
import com.pessimisticit.scarcitybackend.entities.templates.Template
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.CrudRepository
import java.util.*


@Configuration
class DbLoader {
    private val log: Logger = LoggerFactory.getLogger(DbLoader::class.java)

    @Bean
    fun initDb(
        templateRepository: CrudRepository<Template, UUID>,
        gameEntityRepository: CrudRepository<GameEntity<*>, UUID>,
        tagRepo: CrudRepository<Tag, UUID>,
        templateProviders: List<ValueProvider<out Template>>,
        gameEntityProviders: List<ValueProvider<GameEntity<*>>>
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            log.info("loading ${TagValue.values().size} tags")
            tagRepo.saveAll(TagValue.values().map {
                Tag(it)
            })

            log.info("${templateProviders.size} template providers found for loading")
            templateProviders
                .flatMap { it.getValues }
                .map {
                    it.apply {
                        id = UUID.nameUUIDFromBytes(label.lowercase().toByteArray())
                    }
                }
                .forEach(templateRepository::save)

            log.info("${gameEntityProviders.size} game object providers found for loading")
            gameEntityProviders
                .flatMap { it.getValues }
                .map { gameEntityRepository.save(it) }
                .forEach { log.info("saved [$it]") }
        }
    }
}

