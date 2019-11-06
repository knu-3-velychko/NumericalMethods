class LagrangePolynomial(val points: Array<Point>) : Method {
    override val result: DoubleArray by lazy {
        val size = points.size
        val res = DoubleArray(size) { 0.0 }
        for (i in points) {
            val tmp = DoubleArray(size) { 0.0 }
            var product = 1.0
            tmp[0] = i.y
            for (j in points) {
                if (i.x == j.x)
                    continue
                product *= i.x - j.x
                var prevResult = 0.0
                for (k in 0 until size) {
                    val newResult = -tmp[k] * j.x + prevResult
                    prevResult = tmp[k]
                    tmp[k] = newResult
                }
            }
            for(k in 0 until size){
                res[k]+=tmp[k]/product
            }
        }

        res
    }
}
