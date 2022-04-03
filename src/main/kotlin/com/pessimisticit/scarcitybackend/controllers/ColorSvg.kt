package com.pessimisticit.scarcitybackend.controllers

import org.springframework.core.io.ResourceLoader
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayOutputStream

@RestController
@RequestMapping("/svg")
class ColorSvg(
    private val resourceLoader: ResourceLoader
) {
    @GetMapping("/{svg}")
    fun svg(
        @PathVariable("svg") svg: String,
        @RequestParam color: String
    ): ResponseEntity<String> {
        val resource = resourceLoader.getResource("classpath:static/images/svg/$svg")
        val inputStream = resource.inputStream
        val result = ByteArrayOutputStream()
        val buffer = ByteArray(1024)
        var length = 0
        val read: () -> Int = {
            length = inputStream.read(buffer)
            length
        }
        while ((read()) != -1) {
            result.write(buffer, 0, length)
        }
        val string = result.toString("UTF-8")
        val colorized = string.replace("fill=\"#000\"","fill=\"$color\"")
        val responseHeaders = HttpHeaders()
        responseHeaders.contentType= MediaType("image","svg+xml")
        responseHeaders.cacheControl = "public, max-age=2678400"
        return ResponseEntity(colorized,responseHeaders,HttpStatus.OK)

    }

}
