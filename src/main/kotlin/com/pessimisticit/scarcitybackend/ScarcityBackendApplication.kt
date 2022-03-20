package com.pessimisticit.scarcitybackend

import com.pessimisticit.scarcitybackend.repositories.EnhancedRepository
import com.pessimisticit.scarcitybackend.repositories.EnhancedRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = EnhancedRepositoryImpl::class)
class ScarcityBackendApplication

fun main(args: Array<String>) {
    runApplication<ScarcityBackendApplication>(*args)
}
