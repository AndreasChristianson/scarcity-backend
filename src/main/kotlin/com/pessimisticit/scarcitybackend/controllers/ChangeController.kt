package com.pessimisticit.scarcitybackend.controllers

import com.pessimisticit.scarcitybackend.controllers.hateoas.ChangeAssembler
import com.pessimisticit.scarcitybackend.entities.changes.Change
import com.pessimisticit.scarcitybackend.repositories.ChangeRepository
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
class ChangeController(
    val changeRepository: ChangeRepository,
    val pagedResourcesAssembler: PagedResourcesAssembler<Change>,
    val changeAssembler: ChangeAssembler,
) {

    @GetMapping("/{id}/changes")
    fun changes(
        @PathVariable("id") id: UUID,
        @PageableDefault(sort = ["gameTime"])pageable: Pageable
    ): ResponseEntity<PagedModel<Change>> {
        val page = changeRepository.findChanges(id, pageable)
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, changeAssembler))
    }
}


