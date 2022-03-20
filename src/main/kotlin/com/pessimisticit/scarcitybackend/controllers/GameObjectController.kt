package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.controllers.hateoas.GameObjectAssembler
import com.pessimisticit.scarcitybackend.entities.GameObject
import com.pessimisticit.scarcitybackend.repositories.GameObjectRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.PagedModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/game-object")
class GameObjectController(
    val gameObjectRepository: GameObjectRepository,
    val pagedResourcesAssembler: PagedResourcesAssembler<GameObject>,
    val gameObjectAssembler: GameObjectAssembler,
) {
    @GetMapping("/{id}")
    fun byId(@PathVariable("id") id: UUID): ResponseEntity<GameObject> {
        return gameObjectRepository.findById(id)
            .map { ResponseEntity.ok(gameObjectAssembler.toModel(it)) }
            .orElse(ResponseEntity.notFound().build())
    }

    @GetMapping("/{id}/children")
    fun children(
        @PathVariable("id") id: UUID,
        @PageableDefault(sort = ["id"])pageable: Pageable
    ): ResponseEntity<PagedModel<GameObject>> {
        val page = gameObjectRepository.findChildren(id, pageable)
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, gameObjectAssembler))
    }

    @GetMapping("/{id}/parent")
    fun parent(
        @PathVariable("id") id: UUID,
    ): ResponseEntity<GameObject> {
        return gameObjectRepository.findParent(id)
            .map { ResponseEntity.ok(gameObjectAssembler.toModel(it)) }
            .orElse(ResponseEntity.notFound().build())
    }
}


