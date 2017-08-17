package groovy.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.CharEncoding

/**
 * Created by I340951 on 8/16/2017.
 */
class JsonNodeDiff {

    Map<String, DiffResult> result = [:]
    def a_value, b_value, a_node, b_node
    
    Map<String, DiffResult> compare() {
        // prepare
        result.clear()
        result_temp.clear()

        ObjectMapper mapper = new ObjectMapper()
        if(a_node == null) a_node = mapper.readTree(a_value)
        if(b_node == null) b_node = mapper.readTree(b_value)
        // a against b
        compareJson(a_node, b_node, null)
        result.putAll(result_temp)
        // b against a
        result_temp.clear()
        compareJson(b_node, a_node, null)
        result_temp.each { key, value ->
            result.computeIfAbsent(key, { it ->
                new DiffResult(key: value.key, a_value: value.b_value, b_value: value.a_value) } )
        }
        return result
    }

    static void demo() throws IOException {
        String t = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8)
        String a = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson2.json"), CharEncoding.UTF_8)
        def diff = new JsonNodeDiff(a_value: t, b_value: a)
        diff.compare().each { entry -> println entry}
    }

    static void demo2() throws IOException {
        String t = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray.json"), CharEncoding.UTF_8)
        String a = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray2.json"), CharEncoding.UTF_8)
        ObjectMapper mapper = new ObjectMapper()
        def a_node = mapper.readTree(t)
        def b_node = mapper.readTree(a)
        def diff = new JsonNodeDiff(a_node: a_node, b_node: b_node)
        diff.compare().each { entry -> println entry}
    }

    static void main(String[] args) throws IOException {
//        aValue_against_bValue()
//        bValue_against_aValue()
        demo2()
    }

    static void compareJson(ObjectNode aValue, ObjectNode bValue, String key) {
        Iterator<String> i = aValue.fieldNames()

        while (i.hasNext()) {
            def field_name = i.next()
            compareJson(aValue.get(field_name), bValue.get(field_name),
                    key != null ? "${key}.${field_name}" : "\$.${field_name}")
        }
    }

    static void compareJson(Object aValue, Object bValue, String key) {
        if ( aValue instanceof ObjectNode ) {
            compareJson((ObjectNode) aValue ,(ObjectNode) bValue, key)
        }else if ( aValue instanceof ArrayNode) {
            compareJson((ArrayNode) aValue ,(ArrayNode) bValue, key)
        }else if(aValue instanceof String ){
            compareJson((String) aValue ,(String) bValue, key)
        }else {
            compareJson(aValue.toString(), bValue.toString(), key)
        }
    }

    static Map<String, DiffResult> result_temp = [:]
    static void compareJson(String str1, String str2, String key) {
        if (!str1.equals(str2)) {
//            println "[${key}] - '${str1}' : '${str2}'"
            result_temp[key] = new DiffResult(key: key, a_value: str1, b_value: str2)
        }
    }

    static void compareJson(ArrayNode aValue, ArrayNode bValue, String key) {
        Iterator i1= aValue.iterator()
        Iterator i2= bValue.iterator()
        int i = 0
        while ( i1.hasNext()) {
            compareJson(i1.next(), i2.next(),
                    key != null ? "${key}[${i}]" : "\$[${i}]")
            i++
        }
    }

    /**
     * debug code
     */
    static void aValue_against_bValue() throws IOException {
        String aValue = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8)
        String bValue = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson2.json"), CharEncoding.UTF_8)

        ObjectMapper mapper = new ObjectMapper()
        def node = mapper.readTree(aValue)
        def node2 = mapper.readTree(bValue)
        println "aValue vs. bValue"
        compareJson(node, node2, null)
        println result_temp
    }

    static void bValue_against_aValue() throws IOException {
        String aValue = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray.json"), CharEncoding.UTF_8)
        String bValue = IOUtils.toString((new JsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray2.json"), CharEncoding.UTF_8)

        ObjectMapper mapper = new ObjectMapper()
        def node = mapper.readTree(aValue)
        def node2 = mapper.readTree(bValue)
        println "bValue vs. aValue"
        compareJson(node2, node, null)
        println result_temp
    }
}
