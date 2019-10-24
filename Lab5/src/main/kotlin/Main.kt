class Main {
    companion object {
        private const val colorReset = "\u001B[0m"
        private const val colorYellow = "\u001B[33m"
        private const val colorCyan = "\u001B[36m"

        private fun printResult(method: JacobiMethod, expected: Array<Double>) {
            println("$colorCyan Jacobi algorithm for eigenvalues$colorReset result: $colorYellow")

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
            //val matrix = MatrixGenerator.getDiagonallyDominantMatrix(size, 100)
            //val matrix = MatrixGenerator.getHilbertMatrix(size)

            val matrix = MatrixGenerator.getSymmetricMatrix(size, 30)

            printMarix(matrix)
            
        }
    }
}
