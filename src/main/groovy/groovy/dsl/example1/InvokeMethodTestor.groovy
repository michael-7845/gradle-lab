package groovy.dsl.example1

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class InvokeMethodTestor {
    def test() {
        println'hello,function name is test'
    }

    def invokeMethod(String name, Object args) {
        println"the other function, name is ${name}"
    }

    static void main(String... args) {
        def testor = new InvokeMethodTestor()
        testor.test()
        testor.hello()
        testor.doSomething()
    }
}
