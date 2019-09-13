import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

class NewtonMethod(
    var x0: Double,
    private val e: Double,
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
        var x = x0
        var xi = x - f(x) / derivative(x)

        iterations = 0
        var i = 0
        while ((xi - x).absoluteValue > e) {
            iterations = iterations?.inc()
            x = xi
            xi = x - f(x) / derivative(x)
            if (iterations ?: 0 >= MAX_ITERATIONS)
                return null
        }
        if(xi.isNaN())
            return null
        return xi
    }
}