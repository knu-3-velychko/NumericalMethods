import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.tan
import kotlin.system.measureTimeMillis

class Main {
    companion object {

        private fun function(x: Double) = sin(x)

        private fun derivativeFunction(x: Double) = cos(x)

        @JvmStatic
        fun main(args: Array<String>) {
            val a = BisectionMethod(-0.9, 1.0, 0.0001, ::function)
            val b = NewtonMethod(0.3, 0.0001, ::function, ::derivativeFunction)
            val c = RelaxationMethod(-0.9, 1.0, 0.0001, ::function, ::derivativeFunction)

            val printResult =
                { name: String, method: Method -> println("$name method result: ${method.run()}") }
            println("Bisection method time: ${measureTimeMillis { printResult("Bisection", a) }}")
            println()
            println("Newton method time: ${measureTimeMillis { printResult("Newton", b) }}")
            println()
            println("Relaxation method time: ${measureTimeMillis { printResult("Relaxation", c) }}")
        }
    }
}