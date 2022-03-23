package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.constants.MAX_DAMAGE_MULTIPLIER
import com.pessimisticit.scarcitybackend.distributions.DistributionConfiguration
import com.pessimisticit.scarcitybackend.distributions.TruncatedGaussianDistribution
import com.pessimisticit.scarcitybackend.images.GameIcon
import com.pessimisticit.scarcitybackend.images.SvgIcon

data class DamageSpecification(
    val damageType: DamageType,
    val damageShape: DamageShape,
    val distribution: DistributionConfiguration
) {
    constructor(
        damageType: DamageType,
        damageShape: DamageShape,
        center: Double
    ) : this(
        damageType = damageType,
        damageShape = damageShape,
        distribution = TruncatedGaussianDistribution(
            center = center,
            stdDev = damageShape.calculateSpread(center),
            max = center * MAX_DAMAGE_MULTIPLIER
        )
    )


    val icon: GameIcon
        get() = SvgIcon(damageShape.icon.uri, damageType.icon.color)
}