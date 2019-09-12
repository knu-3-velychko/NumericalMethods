import kotlin.math.absoluteValue

class NewtonMethod(
    var x0: Double,
    private val e: Double,
    val f: (x: Double) -> Double,
    val derivative: (x: Double) -> Double
) : Method {
    override fun run(): Double {
        var x = x0
        var xi = x - f(x) / derivative(x)
        var itNumber = 0
        while ((xi - x).absoluteValue > e) {
            x = xi
            xi = x - f(x) / derivative(x)
            itNumber++
        }
        println("Number of iterations $itNumber")
        return xi
    }
}