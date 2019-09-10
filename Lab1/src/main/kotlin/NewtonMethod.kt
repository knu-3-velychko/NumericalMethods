import kotlin.math.absoluteValue

class NewtonMethod(
    var x0: Float,
    private val e: Float,
    val f: (x: Float) -> Float,
    val derivative: (x: Float) -> Float
) : Method {
    override fun run(): Float {
        var x = x0
        var xi = x - f(x) / derivative(x)
        var tmp: Float
        var itNumber = 0
        while ((xi - x).absoluteValue > e) {
            tmp = xi
            xi = x - f(x) / derivative(x)
            x = tmp
            itNumber++
        }
        println("Number of iterations $itNumber")
        return xi
    }
}