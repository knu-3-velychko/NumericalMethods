import kotlin.math.absoluteValue

class BisectionMethod(
    private var a: Double,
    private var b: Double,
    private val e: Double,
    val f: (x: Double) -> Double
) :
    Method {
    override fun run(): Double {
        var itNumber = 0
        var c = 0.0
        while ((b - a).absoluteValue > e) {
            itNumber++
            c = (a + b) / 2
            if (f(a) * f(c) < 0)
                b = c
            if (f(c) * f(b) < 0)
                a = c
        }
        println("Number of iterations $itNumber")
        return c
    }
}


