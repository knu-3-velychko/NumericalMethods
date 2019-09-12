import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

class BisectionMethod(
    private var a: Double,
    private var b: Double,
    private val e: Double,
    val f: (x: Double) -> Double
) :
    Method {

    val result: Double? by lazy {
        var tmp: Double? = null
        time = measureTimeMillis { tmp = calculate() }
        tmp
    }

    var iterations: Int? = null

    var time: Long? = null

    override fun calculate(): Double? {
        var c = 0.0
        iterations = 0
        while ((b - a).absoluteValue > e) {
            iterations++
            c = (a + b) / 2
            if (f(a) * f(c) < 0)
                b = c
            if (f(c) * f(b) < 0)
                a = c
        }
        println("Number of iterations $iterations")
        this.iterations = iterations
        return c
    }
}


