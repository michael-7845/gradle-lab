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

    static void _main() {

    }

    static void main(String... args) {
        demo2()
    }
}
