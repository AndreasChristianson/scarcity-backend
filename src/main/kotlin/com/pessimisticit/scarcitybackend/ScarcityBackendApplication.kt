package com.pessimisticit.scarcitybackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
//@EnableJpaRepositories(basePackageClasses = [RepoMarker::class])
class ScarcityBackendApplication

fun main(args: Array<String>) {
    runApplication<ScarcityBackendApplication>(*args)
}
