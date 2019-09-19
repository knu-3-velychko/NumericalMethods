
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Luv u <3")
            val array = Array(4) { i -> (i + 1).toDouble() }

            val matrix = MatrixGenerator.getRandomMatrix(4, 30, array)
            val gaussMethod = GaussMethod(matrix, 4)
            for (i in gaussMethod.result) {
                println(i)
            }
        }
    }
}