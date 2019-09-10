class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a = BisectionMethod(-2F, 5F, 0.00001F, ::function)
            println(a.run())
        }
    }
}