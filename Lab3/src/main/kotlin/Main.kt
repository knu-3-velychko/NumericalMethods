import java.io.File

class Main {
    companion object {
        val colorReset = "\u001B[0m"
        val colorYellow = "\u001B[33m"
        val colorCyan = "\u001B[36m"

        fun printResult(algo: PageRank) {
            println(colorCyan + "PageRank" + colorReset + " result vector: " + colorYellow)
            for (i in algo.result) {
                println(i)
            }
            println(colorCyan + "PageRank" + colorReset + " result vector max value: " + colorYellow + "${algo.maxValue}")
            println(colorCyan + "Winned page: " + colorYellow + "${algo.pos}")
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val algo = PageRank("0.txt", 0.000001)
            printResult(algo)
            println("Million hugs <3")
        }
    }
}