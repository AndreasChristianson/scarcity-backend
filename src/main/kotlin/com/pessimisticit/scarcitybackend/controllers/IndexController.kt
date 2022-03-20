package com.pessimisticit.scarcitybackend.controllers

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.http.HttpRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import javax.servlet.http.HttpServletRequest

class IndexController {
}


@RestController
@RequestMapping("/")
class MonitoringController(
    val requestMappingHandlerMapping : RequestMappingHandlerMapping
) {
    @GetMapping()
    fun getEndpoints(
         request: HttpServletRequest
    ) : ResponseEntity<List<String>> {
        return ResponseEntity.ok(
            requestMappingHandlerMapping
                .handlerMethods
                .flatMap {
                    it.key.patternValues
                }
        )
    }
}