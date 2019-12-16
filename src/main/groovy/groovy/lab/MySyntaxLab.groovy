package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MySyntaxLab {
    static void demo1() {
        int line = 1
        println (line ?: 0)
//        println (line ?: 0).class
        int x = (line ?: 0)
        println x
        println x.class
    }

    static void demo2() {
//        System.getProperty("env") == null ? "dev" : System.getProperty("env")
        def x = System.getenv("WIREMOCK_LOG") ?: 'false'
        def y = new Boolean(System.getenv("WIREMOCK_LOG") ?: 'false')
        println x
        println x.class
        println y
        println y.class
    }

    static void demo3() {
        def x = 3, y = 2.91
        println Math.abs(x-y) < 0.1
    }

    static void demo4() {
        def i = 1
        println i++
//        println ++i

        def b = true
        b &= true
        println b
        b &= false
        println b
        b &= true
        println b
    }

    static void _main() {

    }

    static void main(String... args) {
        demo4()
    }
}
