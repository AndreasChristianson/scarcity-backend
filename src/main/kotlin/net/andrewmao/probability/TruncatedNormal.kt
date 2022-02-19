package net.andrewmao.probability

import org.apache.commons.math3.distribution.AbstractRealDistribution
import org.apache.commons.math3.exception.OutOfRangeException
import org.apache.commons.math3.random.RandomGenerator
import org.apache.commons.math3.random.Well19937c

/**
 * Truncated normal distribution, mostly from
 * http://en.wikipedia.org/wiki/Truncated_normal_distribution
 * However, that page has some errors, which have been corrected.
 *
 * @author mao
 */
class TruncatedNormal : AbstractRealDistribution {
    private val mu: Double
    private val sigma: Double
    private val cdfA: Double
    private val cdfB: Double
    private val Z: Double
    private val alpha: Double
    private val beta: Double
    private val aa: Double
    private val bb: Double

    constructor(
        rng: RandomGenerator?,
        mean: Double,
        sd: Double,
        lb: Double,
        ub: Double
    ) : super(rng) {
        require(!(mean.isNaN() || sd.isNaN() || lb.isNaN() || ub.isNaN())) { "Cannot take NaN as an argument" }
        mu = mean
        sigma = sd
        cdfA = NormalDist.cdf01((lb - mu) / sigma)
        cdfB = NormalDist.cdf01((ub - mu) / sigma)
        Z = cdfB - cdfA
        alpha = (lb - mu) / sigma
        beta = (ub - mu) / sigma
        aa = lb
        bb = ub
    }

    private constructor(
        mean: Double,
        sd: Double,
        lb: Double,
        ub: Double,
        cdf_a: Double,
        cdf_b: Double
    ) : super(
        Well19937c()
    ) {
        mu = mean
        sigma = sd
        this.cdfA = cdf_a
        this.cdfB = cdf_b
        Z = cdf_b - cdf_a
        alpha = (lb - mu) / sigma
        beta = (ub - mu) / sigma
        aa = lb
        bb = ub
    }

    constructor(
        mean: Double,
        sd: Double,
        lb: Double,
        ub: Double
    ) : this(Well19937c(), mean, sd, lb, ub)

    override fun probability(x: Double): Double {
        return 0.0
    }

    override fun density(x: Double): Double {
        return if (x <= aa || x >= bb) {
            0.0
        } else {
            NormalDist.density01((x - mu) / sigma) / (sigma * Z)
        }
    }

    override fun cumulativeProbability(x: Double): Double {
        if (x <= aa) {
            return 0.0
        } else if (x >= bb) {
            return 1.0
        }
        val u: Double = NormalDist.cdf01((x - mu) / sigma)
        return (u - cdfA) / Z
    }

    @Throws(OutOfRangeException::class)
    override fun inverseCumulativeProbability(p: Double): Double {
        if (p < 0.0 || p > 1.0) {
            throw OutOfRangeException(p, 0, 1)
        }
        val `val` = p * Z + cdfA
        return mu + sigma * NormalDist.inverseF01(`val`)
    }

    override fun getNumericalMean(): Double {
        val phi_a: Double = NormalDist.density01(alpha)
        val phi_b: Double = NormalDist.density01(beta)
        return mu + (phi_a - phi_b) * sigma / Z
    }

    override fun getNumericalVariance(): Double {
        val phiA: Double = NormalDist.density01(alpha)
        val phiB: Double = NormalDist.density01(beta)
        val sq = (phiA - phiB) / Z
        val br = 1 + (alpha * phiA - beta * phiB) / Z - sq * sq
        return sigma * sigma * br
    }

    override fun sample(): Double {
        val `val` = super.random.nextDouble() * Z + cdfA
        return mu + sigma * NormalDist.inverseF01(`val`)
    }

    override fun getSupportLowerBound(): Double {
        return aa
    }

    override fun getSupportUpperBound(): Double {
        return bb
    }

    override fun isSupportLowerBoundInclusive(): Boolean {
        return false
    }

    override fun isSupportUpperBoundInclusive(): Boolean {
        return false
    }

    override fun isSupportConnected(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 3279850685379757823L
    }
}