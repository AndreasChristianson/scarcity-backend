package com.pessimisticit.scarcitybackend.controllers.hateos

import com.pessimisticit.scarcitybackend.controllers.ChangeController
import com.pessimisticit.scarcitybackend.controllers.GameObjectController
import com.pessimisticit.scarcitybackend.entities.GameObject
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Component

@Component
class GameObjectAssembler : RepresentationModelAssemblerSupport<GameObject, GameObject>(
    GameObjectController::class.java, GameObject::class.java,
) {
    override fun toModel(entity: GameObject): GameObject {
        return entity
            .add(linkTo(GameObjectController::class.java).slash(entity.id).slash("children").withRel("children"))
            .add(linkTo(GameObjectController::class.java).slash(entity.id).slash("parent").withRel("parent"))
            .add(linkTo(ChangeController::class.java).slash(entity.id).slash("changes").withRel("changes"))
            .add(linkTo(GameObjectController::class.java).slash(entity.id).withSelfRel())
    }
}