import kotlin.system.measureTimeMillis

class Main {
    companion object {

        private fun function(x: Float) = x * x * x + 8

        private fun derivativeFunction(x: Float) = 3 * x * x

        @JvmStatic
        fun main(args: Array<String>) {
            val a = BisectionMethod(-10F, 3F, 0.00001F, ::function)
            val b = NewtonMethod(1F, 0.00001F, ::function, ::derivativeFunction)

            val printResult =
                { name: String, method: Method -> println("$name method result: ${method.run()}") }
            println("Bisection method time: ${measureTimeMillis { printResult("Bisection", a) }}")
            println("Newton method time: ${measureTimeMillis { printResult("Newton", b) }}")
        }
    }
}