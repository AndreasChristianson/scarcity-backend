package com.pessimisticit.scarcitybackend.controllers.hateos

import com.pessimisticit.scarcitybackend.controllers.ChangeController
import com.pessimisticit.scarcitybackend.controllers.GameObjectController
import com.pessimisticit.scarcitybackend.entities.changes.Change
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component

@Component
class ChangeAssembler: RepresentationModelAssemblerSupport<Change, Change>(
    ChangeController::class.java, Change::class.java,
) {
    override fun toModel(entity: Change): Change {
        return entity
            .add(linkTo(GameObjectController::class.java).slash(entity.gameObject.id).withRel("game-object"))
    }
}