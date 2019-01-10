package hamcrest.matcher.demo

import static org.junit.Assert.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;


/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class UsingHamcrest {
    static void _main() {
        String cadena = "Esta es una cadena";
        Long longValue = new Long(10L);
        List<String> lista = new ArrayList<String>();
        lista.add("Uno");
        lista.add("Dos");
        lista.add("Tres");

        assertThat(longValue, not(is(4L)));
        assertThat(longValue, equalTo(10L));
        assertThat(cadena, equalTo("Esta es una cadena"));
        assertThat(cadena, equalToIgnoringCase("Esta es UNa CADENa"));

        // lista = ["Uno", "Dos", "Tres"]
        assertThat(lista, hasItem("Uno"));
        assertThat(lista, hasItems("Dos", "Tres"));

        assertThat(5L, isOneOf(4L, 3L, 5L));

        assertThat(cadena, is(not(nullValue())));
        assertThat(cadena, is(notNullValue()));

        // lista = ["Uno", "Dos", "Tres", "s"]
        lista.add("s");
        assertThat(lista, allOf(not(hasItems("1", "3")), hasItem("s")));

        // lista = ["Uno", "Dos", "Tres", "s", "5"]
        lista.add("5");
        assertThat(lista, anyOf(hasItem("5"), hasItem("10"), hasItem("1")));

        assertThat(Arrays.asList("foo", "bar"), containsInAnyOrder("bar", "foo"))
        assertThat(Arrays.asList("foo", "bar"), contains(Arrays.asList(equalTo("foo"), equalTo("bar"))))
        assertThat(Arrays.asList("foo", "bar"), hasItem("bar"));
        assertThat(Arrays.asList("foo", "bar"), hasItems("bar", "foo"));
        assertThat(Arrays.asList("foo", "bar").toArray(), hasItemInArray("bar"));
        assertThat(Arrays.asList("foo", "bar"), not(containsInAnyOrder("bar", "foa")));
        assertThat(Arrays.asList("foo", "bar"), contains("foo", "bar"));

        assertThat(cadena, is(anything()));

        assertThat(cadena, not(startsWith("is")));
        assertThat(cadena, not(endsWith("Not")));
        assertThat("is a good, Not", allOf(startsWith("is"), endsWith("Not")));
    }

    static void anyOf_demo() {
        def lista = [3, 1, 2]
        assertThat(lista, anyOf(equalTo([3,2,1]), equalTo([3,1,2])));
    }

    static void main(String... args) {
//        _main()
        anyOf_demo()
    }
}
