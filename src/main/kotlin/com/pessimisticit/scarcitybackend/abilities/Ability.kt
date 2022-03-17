package com.pessimisticit.scarcitybackend.abilities

import com.pessimisticit.scarcitybackend.constants.Activation
import com.pessimisticit.scarcitybackend.constants.TURN_DURATION
import com.pessimisticit.scarcitybackend.effects.AbilityEffect
import com.pessimisticit.scarcitybackend.interfaces.Displayable

abstract class Ability : Displayable {
    open val cost: Double
        get() = 0.0
    open val useTime: Long
        get() = TURN_DURATION
    open val activationProfile
        get() = Activation.IMMEDIATE
    abstract val effect: AbilityEffect
}
