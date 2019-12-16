package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyBooleanLab {
    static void demo1() {
        [[true, false], [true, true], [false, true], [false, false], ].each {
            Boolean a = it[0], b = it[1]
            println "----${a}, ${b}"
            println "!a && !b = ${!a && !b}"
            println "!(a || b) = ${!(a || b)}"
        }
    }

    static void demo2() {

    }

    static void _main() {
        demo1()
    }

    static void main(String... args) {
        _main()
    }
}
