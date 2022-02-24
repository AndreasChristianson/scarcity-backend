package com.pessimisticit.scarcitybackend.mechanics.damage

import com.pessimisticit.scarcitybackend.distributions.DistributionConfiguration
import com.pessimisticit.scarcitybackend.distributions.TruncatedGaussianDistribution
import com.pessimisticit.scarcitybackend.interfaces.Displayable
import java.net.URI

data class DamageSpecification(
    val damageType: DamageType,
    val damageShape: DamageShape,
    val distribution: DistributionConfiguration
) : Displayable {
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
            max = center * 1.25
        )
    )

    override val name: String
        get() = "${distribution.name} $damageType damage"
    override val description: String
        get() = "${distribution.average} $damageShape $damageType damage on average"
    override val icon: URI
        get() = damageType.icon
}