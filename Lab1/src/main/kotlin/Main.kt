class Main {
    companion object {

        private fun function(x: Float) = x * x * x

        private fun derivativeFunction(x: Float) = 3 * x * x

        @JvmStatic
        fun main(args: Array<String>) {
            val a = BisectionMethod(-2F, 5F, 0.00001F, ::function)
            val b = NewtonMethod(1F, 0.00001F, ::function, ::derivativeFunction)
            println(a.run())
            println(b.run())
        }
    }
}