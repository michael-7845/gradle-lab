package spock.demo

import spock.lang.Specification

/**
 * Created by I340951 on 8/1/2017.
 */
class BasicDemoSpec extends Specification {
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
}
