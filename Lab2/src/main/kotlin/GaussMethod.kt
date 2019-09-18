import java.math.BigDecimal

class GaussMethod(val matrix: Array<Array<BigDecimal>>, val vector: Array<BigDecimal>) {
    val result: Array<BigDecimal> by lazy{
        vector
    }
}