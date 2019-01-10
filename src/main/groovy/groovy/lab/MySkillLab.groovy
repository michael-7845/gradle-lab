package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MySkillLab {
    static void demo1() {
        def a = null
        def b = a ?: 'none'
        println b
        println b.size()

        a = [b:2, c:3]
        println a?.b
        a = null
        println a?.b
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
