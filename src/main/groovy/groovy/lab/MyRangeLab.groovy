package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyRangeLab {
    static void demo1() {
        for(i in 1..5) { println i }
        for(i in 1..<5) { println i }
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
