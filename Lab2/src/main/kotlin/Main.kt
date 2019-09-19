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

            val jacobiMethod = JacobiMethod(matrix, Array(4) { 0.0 }, 4, 0.001)
            for(i in jacobiMethod.result){
                println(i)
            }

            val seidelMethod=SeidelMethod(matrix, Array(4){0.0},4,0.001)
            for(i in seidelMethod.result){
                println(i)
            }
        }
    }
}