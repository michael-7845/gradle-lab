package spock.demo

import spock.lang.Specification

import static org.hamcrest.Matchers.hasItem
import static org.hamcrest.Matchers.hasItems
import static org.junit.Assert.assertThat

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
}
