package com.pessimisticit.scarcitybackend.abilities.evocations

import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.effects.AbilityEffect
import com.pessimisticit.scarcitybackend.effects.LightAura
import java.net.URI

object EvokeLight: Ability() {
    override val effect: AbilityEffect
        get() = LightAura
    override val name: String
        get() = "evoke light"
    override val description: String
        get() = "creates light in large area"
    override val icon: URI
        get() = URI("http://temp.com")
}
