package spock.demo

import spock.lang.Ignore
import spock.lang.Specification

/**
 * Created by I340951 on 8/1/2017.
 */
class BasicDemoSpec extends Specification {
    @Ignore
    def listDemo() {
        given:
        def a = [1, 2, 3, 4]
        def a2 = [1, 2, 3, 4]
        def b = [1, 2, 4, 3]
        assert a == a2
        assert a != b

        expect:
        a == b
    }

    def '''where clause #a #b #c'''(a, b, c) {
        when: '''when #a, #b, #c'''
        println "a: ${a}, b: ${b}, c: ${c}"
        then: '''pass '''
        true
        where: '''a, b, c'''
//        a << [1, 2, 3]
//        b << [4, 5, 6]
//        c = 7

//        a << [1, 7, 0]
//        b << [3, 4, 0]
//        c << [3, 7, 0]

//        a = 3
//        b = Math.random() * 100
//        c = a > b ? a : b

        a | _
        3 | _
        7 | _
        0 | _

        b << [5, 0, 0]

        c = a > b ? a : b
    }
}
