import kotlin.math.absoluteValue
import kotlin.math.sign
import kotlin.system.measureTimeMillis

class RelaxationMethod(
    var a: Double,
    var b: Double,
    val c: Double,
    val e: Double,
    val f: (x: Double) -> Double,
    val derivative: (x: Double) -> Double,
    val MAX_ITERATIONS: Int = 100_00
) : Method {
    override val result: Double? by lazy {
        var tmp: Double? = null
        time = measureTimeMillis { tmp = calculate() }
        tmp
    }

    override var iterations: Int? = null

    override var time: Long? = null

    override fun calculate(): Double? {
        var x = (a + b) / 2
        var xi = x - c * f(x)

        iterations = 0

        while ((xi - x).absoluteValue > e) {
            iterations = iterations?.inc()
            x = xi
            xi = x - c * f(x)
            if (iterations ?: 0 >= MAX_ITERATIONS)
                return null
        }
        if (xi.isNaN() || xi == Double.POSITIVE_INFINITY || xi == Double.NEGATIVE_INFINITY)
            return null
        return xi
    }
}

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    require(start.isFinite())
    require(endInclusive.isFinite())
    require(step > 0.0) { "Step must be positive, was: $step." }
    val sequence = generateSequence(start) { previous ->
        if (previous == Double.POSITIVE_INFINITY) return@generateSequence null
        val next = previous + step
        if (next > endInclusive) null else next
    }
    return sequence.asIterable()
}