package groovy.dsl.groovydemo

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class ClosureLab {
    /*
     *         https://www.cnblogs.com/zqlxtt/p/5741297.html
     */
    static void lab1() {
        def x = 1
        def gs = "x = ${x}"
        assert gs == 'x = 1'

        x = 2
        assert gs == 'x = 2'
    }

    static void lab2() {
        def x = 1
        def gs = "x = ${-> x}"
        assert gs == 'x = 1'

        x = 2
        assert gs == 'x = 2'
    }

    static void main(String... args) {
//        lab1()
        lab2()
    }
}
