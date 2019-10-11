class Main {
    companion object {
        private const val colorReset = "\u001B[0m"
        private const val colorYellow = "\u001B[33m"
        private const val colorCyan = "\u001B[36m"

        private fun printResult(name: String, method: Method, expected: Array<Double>) {
            println("$colorCyan$name$colorReset result: $colorYellow")
            for (i in method.result)
                print("$i  ")
            println()
            println("$colorCyan$name$colorReset expected result: $colorYellow")
            for (i in expected)
                print("$i  ")
            println()
            println("$colorCyan$name$colorReset number of iterations: $colorYellow${method.iterations}")
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
            val size = 4
            println("I luv ya very much, my sweetheart <3")
            val root = Array(4) { i -> (i + 1).toDouble() }

            //val matrix = MatrixGenerator.getRandomMatrix(4, 30, array)
            //val matrix = MatrixGenerator.getDiagonallyDominantMatrix(4, 30, array)
            val matrix = MatrixGenerator.getHilbertMatrix(size)
            val vector = MatrixGenerator.getVector(size, matrix, root)

            val method = LUFactorization(matrix, vector, size, 0.00001)

            printResult("LU Decomposition", method, root)

        }
    }
}