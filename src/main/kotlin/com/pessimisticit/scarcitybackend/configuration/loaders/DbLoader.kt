package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.GameEntity
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
        gameEntityRepository: CrudRepository<GameEntity, UUID>,
        gameEntityProviders: List<ValueProvider<GameEntity>>
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            log.info("${gameEntityProviders.size} game object providers found for loading")
            gameEntityProviders
                .onEach { log.info("working on [${it.javaClass.name}]") }
                .flatMap { it.getValues }
                .map { gameEntityRepository.save(it) }
                .forEach { log.info("saved [$it]") }
        }
    }
}

