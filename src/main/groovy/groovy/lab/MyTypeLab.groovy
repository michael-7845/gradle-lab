package groovy.lab

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class MyTypeLab {
    static void demo1() {
        println 'test' as Boolean //true
        println 'test'.toBoolean() //false
        println new Boolean('test') //false

        println 'true'.toBoolean() //false
        println 'True'.toBoolean() //false
        println 'TRUE'.toBoolean() //false
        println 'tRUE'.toBoolean() //false
        println 'false'.toBoolean() //false
        println 'False'.toBoolean() //false
        println 'FALSE'.toBoolean() //false
        println 'fALSE'.toBoolean() //false

        assert "y".toBoolean()
        assert 'TRUE'.toBoolean()
        assert '  trUe  '.toBoolean()
        assert " y".toBoolean()
        assert "1".toBoolean()

        assert ! 'other'.toBoolean()
        assert ! '0'.toBoolean()
        assert ! 'no'.toBoolean()
        assert ! '  FalSe'.toBoolean()
    }

    static void demo2() {
        println 1.getClass()
        Map map = ['a': 1]
        println map.getClass()

        println (1 instanceof Integer)
        println (map instanceof Map)
    }

    static void _main() {
        demo2()
    }

    static void main(String... args) {
        _main()
    }


}
