package com.pessimisticit.scarcitybackend

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    servers = [
        Server(url = "http://localhost:8080"),
        Server(url = "https://scarcity-backend.fly.dev")
    ]
)
class ScarcityBackendApplication

fun main(args: Array<String>) {
    runApplication<ScarcityBackendApplication>(*args)
}
