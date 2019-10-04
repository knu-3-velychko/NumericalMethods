class Main {
    companion object {
        val colorReset = "\u001B[0m"
        val colorYellow = "\u001B[33m"
        val colorCyan = "\u001B[36m"

        fun printResult(name: String, method: Method, expected: Array<Double>) {
            println(colorCyan + name + colorReset + " result: " + colorYellow)
            for (i in method.result)
                print("$i  ")
            println()
            println(colorCyan + name + colorReset + " expected result: " + colorYellow)
            for (i in expected)
                print("$i  ")
            println()
            println(colorCyan + name + colorReset + " number of iterations: " + colorYellow + method.iterations)
            println()
        }

        fun printMarix(matrix: Array<Array<Double>>) {
            println(colorReset + "Matrix: " + colorYellow)
            for (i in matrix) {
                for (j in i)
                    print("$j ")
                println()
            }
            println()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            println("Luv u <3")
            val array = Array(4) { i -> (i + 1).toDouble() }

            //val matrix = MatrixGenerator.getRandomMatrix(4, 30, array)
            //val matrix = MatrixGenerator.getDiagonallyDominantMatrix(4, 30, array)
            val matrix = MatrixGenerator.getHilbertMatrix(4, array)

            printMarix(matrix)

            val gaussMethod = GaussMethod(matrix, 4)
            printResult("Gauss method", gaussMethod, array)

            val jacobiMethod = JacobiMethod(matrix, Array(4) { 0.0 }, 4, 0.001)
            printResult("Jacobi method", jacobiMethod, array)

            val seidelMethod = SeidelMethod(matrix, Array(4) { 0.0 }, 4, 0.001)
            printResult("Seidel method", seidelMethod, array)
        }
    }
}