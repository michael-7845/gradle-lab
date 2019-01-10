package groovy.classic.c9closure

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class C9_1 {
    static void sample1() {
        def clos = {println 'hello world'}
        clos.call()
    }

    static void sample2() {
        def clos = {param -> println "hello $param"}
        clos.call('michael')
        clos('landy')
    }

    static void sample3() {
        def clos = {println "hello ${it}"}
        clos.call('michael')
        clos('landy')
    }

    static void sample4() {
        def greeting = 'hello'
        def clos = {param -> println "${greeting} ${param}"}
        clos.call('michael')

        greeting = 'welcome'
        clos('landy')
    }

    static def demo(clo) {
        def greeting = 'bonjour'
        clo.call('ken')
    }

    static void sample5() {
        def greeting = 'hello'
        def clos = {param -> println "${greeting} ${param}"}
        clos.call('world')

        greeting = 'welcome'
        clos('world')

        demo(clos)
    }

    static void sample6() {
        def greeting = 'hello'
        def clos = {param -> println "${greeting} ${param}"}

        println clos.getClass()

        demo(clos)

//        demo() clos // exception ... java.lang.NullPointerException: Cannot invoke method call() on null object
        demo() {param -> println "welcome ${param}"}

        demo clos
        demo {param -> println "welcome ${param}"}
    }

    static void sample6_1() {
        1.upto(10) {println it}
        20.upto(25) {println it*2}
    }

    static void sample7() {
        def factorial = 1
        1.upto(5) {num -> factorial *= num}
        println "Factorial(5): ${factorial}"
    }

    static void _main() {
        sample6()
    }

    static void main(String... args) {
        _main()
    }
}
