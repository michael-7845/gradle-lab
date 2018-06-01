package spock.demo

import spock.lang.Specification

import static org.junit.Assert.assertThat
//import static org.hamcrest.Matchers.*
import static org.hamcrest.Matchers.hasItem
//import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.hamcrest.CoreMatchers.anyOf

/**
 * Created by I340951 on 8/8/2017.
 */
class HamcrestDemoSpec extends Specification {
    def "collection matchers"() {
        given:
        def l = [1, 2, 3]
        def e = [1, 3]
        expect:
        for(i in e) {
            assertThat(l, hasItem(i));
        }
    }

    def "collection string matchers"() {
        given:
        def l = ["abc", "efg", "hij"]
        def e = ["abc", "efg"]
        expect:
        for(i in e) {
            assertThat(l, hasItem(i));
        }

        assertThat(l.containsAll(e), is(true));
    }

    def "assertThat anyof"() {
        expect:
        assertThat(1, anyOf(equalTo(0), equalTo(1)))
    }
}
