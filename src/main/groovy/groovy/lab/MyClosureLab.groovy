package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyClosureLab {
    static void demo1() {
        1.upto(10) {
            it -> if(it > 6) { return }
                println it
        }

        1.upto(10) {
            println it
        }
    }

    static void func(Closure clos) {
        clos()
    }

    static void demo2(number) {
        Closure clo = {println (number+1)}
        func(clo)
    }

    static void _main() {
        demo1()
    }

    static void main(String... args) {
        demo2(10)
        _main()
    }
}
