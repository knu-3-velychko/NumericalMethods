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

        fun printMarix(matrix: Matrix) {
            println(colorReset + "Matrix: " + colorYellow)
            for (i in 0 until matrix.size) {
                for (j in 0 until matrix.size)
                    print("${matrix[i, j]} ")
                println()
            }
            println()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val size = 3
            val root = Array(size) { i -> (i + 1).toDouble() }

            //val matrix = MatrixGenerator.getRandomMatrix(size, 30)
            val matrix = MatrixGenerator.getDiagonallyDominantMatrix(size, 100)
            printMarix(matrix)
            //val matrix = MatrixGenerator.getHilbertMatrix(size)
            val vector = MatrixGenerator.getVector(size, matrix, root)

            val gaussMethod = LUFactorization(matrix, vector, size, 0.0000000001)
            printResult("LU Decomposition", gaussMethod, root)
            var gaussNorm = 0.0
            for (i in 0 until size)
                gaussNorm += (root[i] - gaussMethod.result[i]) * (root[i] - gaussMethod.result[i])
            println("$colorReset Norm: $colorYellow$gaussNorm")

            val jacobiMethod = JacobiMethod(matrix, vector, Array(size) { 1.0 }, size, 0.0000000001)
            printResult("Jacobi Method", jacobiMethod, root)
            var jacobiNorm = 0.0
            for (i in 0 until size)
                jacobiNorm += (root[i] - jacobiMethod.result[i]) * (root[i] - jacobiMethod.result[i])
            println("$colorReset Norm: $colorYellow$jacobiNorm")

            val seidelMethod = SeidelMethod(matrix, vector, Array(size) { 0.0 }, size, 0.0000000001)
            printResult("Seidel Method", seidelMethod, root)
            var seidelNorm = 0.0
            for (i in 0 until size)
                seidelNorm += (root[i] - seidelMethod.result[i]) * (root[i] - seidelMethod.result[i])
            println("$colorReset Norm: $colorYellow$seidelNorm")

        }
    }
}
