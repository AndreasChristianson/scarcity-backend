package com.pessimisticit.scarcitybackend.abilities.evocations

import com.pessimisticit.scarcitybackend.abilities.Ability
import com.pessimisticit.scarcitybackend.constants.svgRoot
import com.pessimisticit.scarcitybackend.effects.AbilityEffect
import com.pessimisticit.scarcitybackend.effects.LightAura
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon
import java.net.URI

object EvokeLight: Ability() {
    override val effect: AbilityEffect
        get() = LightAura
    override val name: String
        get() = "evoke light"
    override val description: String
        get() = "creates light in large area"
    override val icon: GameIcon
        get() = SvgIcon(URI("$svgRoot/aura.svg"),"yellow")
}
