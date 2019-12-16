package groovy.json

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.CharEncoding

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class JsonUtil {

    static Map read_json(String str) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.readTree(str)
    }

    static void _main() {
        String json = IOUtils.toString(this.getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8);
        println read_json(json)
    }

    static void main(String... args) {
        _main()
    }
}
