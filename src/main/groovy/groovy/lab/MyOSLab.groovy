package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyOSLab {
    static void demo1() {
        println '============ system properties'
        println System.properties
        println '============ environment variables'
        println System.getenv()
    }

    static void demo2() {

    }

    static void _main() {

    }

    static void main(String... args) {
        demo1()
    }
}
