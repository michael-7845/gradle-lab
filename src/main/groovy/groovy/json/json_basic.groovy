package groovy.json

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.CharEncoding

/**
 * Created by I340951 on 8/15/2017.
 */

println "demo 1"
ObjectMapper mapper = new ObjectMapper();
mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

String json = IOUtils.toString(this.getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8);
String json2 = IOUtils.toString(this.getClass().getResourceAsStream("/json/TestJson2.json"), CharEncoding.UTF_8);

def node = mapper.readTree(json)
def node2 = mapper.readTree(json2)
println node
println node2
println node == node2
