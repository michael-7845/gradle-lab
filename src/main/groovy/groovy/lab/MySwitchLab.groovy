package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MySwitchLab {
    static def testSwitch(val) {
        def result
        switch (val) {
            case ~/^Switch.*Groovy$/:
                result = 'Pattern match'
                break
            case BigInteger:
                result = 'Class isInstance'
                break
            case 60..90:
                result = 'Range contains'
                break
            case [21, 'test', 9.12]:
                result = 'List contains'
                break
            case 42.056:
                result = 'Object equals'
                break
            case { it instanceof Integer && it < 50 }:
                result = 'Closure boolean'
                break
            default:
                result = 'Default'
                break
        }
        result
    }

    static void demo1() {
        assert 'Pattern match' == testSwitch("Switch to Groovy")
        assert 'Class isInstance' == testSwitch(42G)
        assert 'Range contains' == testSwitch(70)
        assert 'List contains' == testSwitch('test')
        assert 'Object equals' == testSwitch(42.056)
        assert 'Closure boolean' == testSwitch(20)
        assert 'Default' == testSwitch('default')
    }

    static void demo2() {
        List l = ['a', 2]
        switch(l) {
            case(['a', 1]):
                println '1'
                break
            case(['a', 2]):
                println '2'
                break
            case(['b', 1]):
                println '3'
                break
            default:
                println '0'
        }

        if(l == ['a', 1]) {
            println '1'
        } else if(l == ['a', 2]) {
            println '2'
        } else if(l == ['b', 1]) {
            println '3'
        } else {
            println '0'
        }
    }

    static void _main() {
        demo2()
    }

    static void main(String... args) {
        _main()
    }


}
