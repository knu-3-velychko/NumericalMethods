import kotlin.math.absoluteValue
import kotlin.math.sign

class RelaxationMethod(
    var a: Double,
    var b: Double,
    val e: Double,
    val f: (x: Double) -> Double,
    val derivative: (x: Double) -> Double
) : Method {
    override fun calculate(): Double? {
        val function = (a..b step 0.01).map { derivative(it) }
        val max = function.max() ?: function[0]
        val min = function.min() ?: function[0]
        val c = 2 / (max + min)

        var x = (a + b) / 2
        var xi = x - c * f(x)
        var itNumber = 0

        while ((xi - x).absoluteValue > e) {
            x = xi
            xi = x - c * f(x) * sign(derivative(x))
            itNumber++
        }
        println("Number of iterations $itNumber")
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