package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.constants.DamageShape
import com.pessimisticit.scarcitybackend.constants.DamageType
import com.pessimisticit.scarcitybackend.distributions.DistributionConfiguration

data class DamageSpecification(
    val damageType: DamageType,
    val damageShape: DamageShape,
    val distribution: DistributionConfiguration
)