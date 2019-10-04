import java.io.File

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val result = PageRank("1.txt", 0.0001).result
            for (i in result)
                println(i)
            println("Million hugs <3")
        }
    }
}