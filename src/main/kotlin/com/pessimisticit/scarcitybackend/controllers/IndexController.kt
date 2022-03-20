package com.pessimisticit.scarcitybackend.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/")
class IndexController(
    val requestMappingHandlerMapping: RequestMappingHandlerMapping
) {
    @GetMapping
    fun getEndpoints(
        request: HttpServletRequest
    ): ResponseEntity<List<String>> {
        return ResponseEntity.ok(
            requestMappingHandlerMapping
                .handlerMethods
                .flatMap {
                    it.key.patternValues
                }
        )
    }
}