import java.math.BigDecimal

fun getRandomMatrix(size: Int, range: Int): Matrix {
    val matrix = Array(size) { Array<BigDecimal>(size) { BigDecimal(0) } };
    val max = BigDecimal(2 * range)
    for (i in 1..size) {
        for (j in 1..size) {
            val randFromDouble = BigDecimal(Math.random())
            val actualRandomDec = randFromDouble.multiply(max) - max / BigDecimal(2)
            matrix[i][j] = actualRandomDec
        }
    }
    return Matrix(matrix)
}

//
//fun getMatrix(size: Int, range: Int): Matrix {
//    val matrix = Array(size) { Array<BigDecimal>(size) { BigDecimal(0) } };
//    val max = BigDecimal(2 * range)
//    for (i in 1..size) {
//        for (j in 1..size) {
//            val randFromDouble = BigDecimal(Math.random())
//            val actualRandomDec = randFromDouble.multiply(max) - max / BigDecimal(2)
//            matrix[i][j] = actualRandomDec
//        }
//    }
//    return Matrix(matrix)
//}