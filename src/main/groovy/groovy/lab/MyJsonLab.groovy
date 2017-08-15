package groovy.lab

import org.apache.commons.lang3.CharEncoding
import org.apache.commons.io.IOUtils;

/**
 * Created by I340951 on 8/14/2017.
 */
class MyJsonLab {
    final static String demoJsonString = IOUtils.toString(this.getClass().getResourceAsStream("/json/metadata.json"),
            CharEncoding.UTF_8
    )

    static void demo1() {
        println demoJsonString
    }

    static void main(String... args) {
        demo1()
    }
}
