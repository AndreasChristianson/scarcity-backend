package net.andrewmao.probability

import kotlin.math.exp
import kotlin.math.ln
import kotlin.math.sqrt

/*
 * Originally from
 * http://www.iro.umontreal.ca/~simardr/ssj/indexe.html
 */
object NormalDist {
    internal const val RAC2PI = 2.5066282746310007 // Sqrt(2*Pi)
    internal const val XBIG = 100.0

    /*
	 * The precision of double is 16 decimals; we shall thus use coeffmax = 24
	 * coefficients. But the approximation is good to 30 decimals of precision
	 * with 44 coefficients.
	 */
    private const val COEFFMAX = 24
    private val NORMAL2_A = doubleArrayOf(
        0.6101430819232004,
        -0.4348412727125775,
        0.1763511936436055,
        -0.06071079560924941,
        0.017712068995694115,
        -0.004321119385567294,
        8.542166768870987E-4,
        -1.2715509060916275E-4,
        1.1248167243671189E-5,
        3.1306388542182096E-7,
        -2.70988068537762E-7,
        3.073762270140769E-8,
        2.515620384817623E-9,
        -1.0289299213203192E-9,
        2.994405211994994E-11,
        2.6051789687266936E-11,
        -2.6348399241719693E-12,
        -6.434045098906365E-13,
        1.1245740180166345E-13,
        1.7281533389986097E-14,
        -4.264101694942375e-15,
        -5.45371977880191e-16,
        1.58697607761671e-16,
        2.0899837844334e-17,
        -5.900526869409e-18,
        -9.41893387554e-19 /*,     2.14977356470e-19,
	    4.6660985008e-20,
	    -7.243011862e-21,
	    -2.387966824e-21,
	    1.91177535e-22,
	    1.20482568e-22,
	    -6.72377e-25,
	    -5.747997e-24,
	    -4.28493e-25,
	    2.44856e-25,
	    4.3793e-26,
	    -8.151e-27,
	    -3.089e-27,
	    9.3e-29,
	    1.74e-28,
	    1.6e-29,
	    -8.0e-30,
	    -2.0e-30
		 */
    )

    fun density01(x: Double): Double {
        return exp(-0.5 * x * x) / RAC2PI
    }

    fun density(mu: Double, sigma: Double, x: Double): Double {
        val z = (x - mu) / sigma
        return exp(-0.5 * z * z) / (RAC2PI * sigma)
    }

    fun cdf01(input: Double): Double {
        /*
		 * Returns P[X < x] for the normal distribution.
		 * As in J. L. Schonfelder, Math. of Computation, Vol. 32,
		 * pp 1232--1240, (1978).
		 */
        var x = input
        val t: Double
        val r: Double
        if (x <= -XBIG) {
            return 0.0
        }
        if (x >= XBIG) {
            return 1.0
        }
        x = -x / Num.RAC2
        if (x < 0) {
            x = -x
            t = (x - 3.75) / (x + 3.75)
            r = 1.0 - 0.5 * exp(-x * x) * Num.evalCheby(NORMAL2_A, COEFFMAX, t)
        } else {
            t = (x - 3.75) / (x + 3.75)
            r = 0.5 * exp(-x * x) * Num.evalCheby(NORMAL2_A, COEFFMAX, t)
        }
        return r
    }

    /**
     * Computes the normal distribution function with mean
     * <SPAN CLASS="MATH"><I>&#956;</I></SPAN> and variance <SPAN CLASS="MATH"><I>&#963;</I><SUP>2</SUP></SPAN>.
     * Uses the Chebyshev approximation ,
     * which gives 16 decimals of precision.
     *
     */
    fun cdf(mu: Double, sigma: Double, x: Double): Double {
        require(sigma > 0) { "sigma <= 0" }
        return cdf01((x - mu) / sigma)
    }

    private val InvP1 = doubleArrayOf(
        16.030495584406623,
        -90.78495926296033,
        186.44914861620987,
        -169.0014273464238,
        65.45466284794487,
        -8.642130115872478,
        0.1760587821390590
    )
    private val InvQ1 = doubleArrayOf(
        14.780647071513831,
        -91.37416702426032,
        210.1579048620532,
        -222.10254121855132,
        107.60453916055124,
        -20.601073032826545,
        0.1E1
    )
    private val InvP2 = doubleArrayOf(
        -0.015238926344072612,
        0.3444556924136125,
        -2.9344398672542478,
        11.763505705217828,
        -22.655292823101103,
        19.12133439658033,
        -5.478927619598319,
        0.237516689024448000
    )
    private val InvQ2 = doubleArrayOf(
        -0.010846516960205995,
        0.2610628885843079,
        -2.406831810439376,
        10.695129973387015,
        -23.71671552159658,
        24.640158943917285,
        -10.01437634978307,
        0.1E1
    )
    private val InvP3 = doubleArrayOf(
        5.6451977709864484E-5,
        0.005350414748789302,
        0.12969550099727353,
        1.0426158549298266,
        2.830267790175449,
        2.6255672879448073,
        2.078974263017492,
        0.7271880623155681,
        0.066816807711805,
        -0.01779100457511176,
        0.0022419563223346345
    )
    private val InvQ3 = doubleArrayOf(
        5.645169986276065E-5,
        0.005350558706793065,
        0.12986615416911648,
        1.0542932232626492,
        3.0379331173522206,
        3.763116853640503,
        3.878285827704201,
        2.0372431817412178,
        0.1E1
    )

    fun inverseF01(u: Double): Double {
        /*
		 * Returns the inverse of the cdf of the normal distribution.
		 * Rational approximations giving 16 decimals of precision.
		 * J.M. Blair, C.A. Edwards, J.H. Johnson, "Rational Chebyshev
		 * approximations for the Inverse of the Error Function", in
		 * Mathematics of Computation, Vol. 30, 136, pp 827, (1976)
		 */
        var i: Int
        val negatif: Boolean
        var y: Double
        var z: Double
        var v: Double
        var w: Double
        var x = u
        require(!(u < 0.0 || u > 1.0)) { "u is not in [0, 1]" }
        if (u <= 0.0) {
            return Double.NEGATIVE_INFINITY
        }
        if (u >= 1.0) {
            return Double.POSITIVE_INFINITY
        }

        // Transform x as argument of InvErf
        x = 2.0 * x - 1.0
        if (x < 0.0) {
            x = -x
            negatif = true
        } else {
            negatif = false
        }
        if (x <= 0.75) {
            y = x * x - 0.5625
            w = 0.0
            v = w
            i = 6
            while (i >= 0) {
                v = v * y + InvP1[i]
                w = w * y + InvQ1[i]
                i--
            }
            z = v / w * x
        } else if (x <= 0.9375) {
            y = x * x - 0.87890625
            w = 0.0
            v = w
            i = 7
            while (i >= 0) {
                v = v * y + InvP2[i]
                w = w * y + InvQ2[i]
                i--
            }
            z = v / w * x
        } else {
            y = if (u > 0.5) {
                1.0 / sqrt(-ln(1.0 - x))
            } else {
                1.0 / sqrt(-ln(2.0 * u))
            }
            v = 0.0
            i = 10
            while (i >= 0) {
                v = v * y + InvP3[i]
                i--
            }
            w = 0.0
            i = 8
            while (i >= 0) {
                w = w * y + InvQ3[i]
                i--
            }
            z = v / w / y
        }
        return if (negatif) {
            if (u < 1.0e-105) {
                val RACPI = 1.772453850905516
                w = exp(-z * z) / RACPI // pdf
                y = 2.0 * z * z
                v = 1.0
                var term = 1.0
                // Asymptotic series for erfc(z) (apart from exp factor)
                i = 0
                while (i < 6) {
                    term *= -(2 * i + 1) / y
                    v += term
                    ++i
                }
                // Apply 1 iteration of Newton solver to get last few decimals
                z -= u / w - 0.5 * v / z
            }
            -(z * Num.RAC2)
        } else z * Num.RAC2
    }

    /**
     * Computes the inverse normal distribution function
     * with mean <SPAN CLASS="MATH"><I>&#956;</I></SPAN> and variance <SPAN CLASS="MATH"><I>&#963;</I><SUP>2</SUP></SPAN>. Uses different
     * rational Chebyshev approximations.
     * Returns 16 decimal digits of precision for
     * <SPAN CLASS="MATH">2.2&#215;10<SUP>-308</SUP> &lt; <I>u</I> &lt; 1</SPAN>.
     *
     */
    fun inverseF(mu: Double, sigma: Double, u: Double): Double {
        require(sigma > 0) { "sigma <= 0" }
        return mu + sigma * inverseF01(u)
    }
}