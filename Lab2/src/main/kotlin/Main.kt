import java.math.BigDecimal

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Luv u <3")
            val matrix = MatrixGenerator.getRandomMatrix(10, 30, Array(10) { i -> BigDecimal(i + 1) })
            val gaussMethod = GaussMethod(matrix, 10)

            for (i in gaussMethod.result) {
                println(i)
            }
        }
    }
}