import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

class BisectionMethod(
    private var a: Double,
    private var b: Double,
    private val e: Double,
    val f: (x: Double) -> Double,
    val MAX_ITERATIONS: Int = 100_00
) :
    Method {

    override val result: Double? by lazy {
        var tmp: Double? = null
        time = measureTimeMillis { tmp = calculate() }
        tmp
    }

    override var iterations: Int? = null

    override var time: Long? = null

    override fun calculate(): Double? {
        var c = 0.0
        iterations = 0
        while ((b - a).absoluteValue > e) {
            iterations = iterations?.inc()
            c = (a + b) / 2
            if (f(a) * f(c) < 0.0)
                b = c
            else if (f(c) * f(b) < 0.0)
                a = c
            else
                return null
            if (iterations ?: 0 >= MAX_ITERATIONS)
                break
        }

        if(c.isNaN())
            return null
        return c
    }
}


