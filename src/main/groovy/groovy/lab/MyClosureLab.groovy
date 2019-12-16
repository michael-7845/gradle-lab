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

    def clos2 = {x, y -> (x+y)}
    def clos3 = {z -> clos2(z, 10) }
    static void demo3() {
        MyClosureLab mcl = new MyClosureLab()
        println mcl.clos3.getClass()
        println mcl.clos3(1)
    }

    static void _main() {
//        demo1()
//        demo2(10)
        demo3()
    }

    static void main(String... args) {
        _main()
    }
}
