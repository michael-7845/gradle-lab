package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyStringLab {
    static void demo1(String... args) {
        println args
    }

    static void demo2(String[] args) {
        demo1(args)
    }

    static void _main() {

    }

    static void main(String... args) {
        demo2('a', 'b', 'c')
    }
}
