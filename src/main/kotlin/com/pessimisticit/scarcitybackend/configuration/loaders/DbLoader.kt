package com.pessimisticit.scarcitybackend.configuration.loaders

import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.entities.rooms.Universe
import com.pessimisticit.scarcitybackend.repositories.ChangeRepository
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import com.pessimisticit.scarcitybackend.services.GameObjectService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Instant
import java.util.*


@Configuration
class DbLoader {
    private val log: Logger = LoggerFactory.getLogger(DbLoader::class.java)

    @Bean
    fun initDb(
        gameObjectRepository: GameObjectRepository,
        changeRepository: ChangeRepository,
        gameObjectService: GameObjectService,
    ): CommandLineRunner {
        return CommandLineRunner { _ ->
            val universe = Universe()
                .apply {
                    id = UUID.fromString("00000000-0000-0000-0000-000000000000")
                    parent = this
                }
            val saved = gameObjectRepository.save(universe)
            changeRepository.save(
                Change().apply {
                    gameObject = universe
                    gameTime = 0
                    stamp = Instant.EPOCH
                    id = UUID.fromString("00000000-0000-0000-0000-000000000000")
                    message = "Bang!"
                }
            )
//            val saved = gameObjectRepository.findById( UUID.fromString("00000000-0000-0000-0000-000000000000"))
            log.debug("Saved $saved")
        }
    }
}

