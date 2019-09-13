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
        if (xi.isNaN() || xi.isInfinite())
            return null
        return xi
    }
}

