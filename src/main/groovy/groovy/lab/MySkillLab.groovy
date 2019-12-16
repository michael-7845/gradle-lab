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

    static List _return_list() {
        [1, 'a']
    }

    static void demo2() {
        def l = _return_list()
        println l
    }

    static void _main() {
        demo2()
    }

    static void main(String... args) {
        _main()
    }
}
