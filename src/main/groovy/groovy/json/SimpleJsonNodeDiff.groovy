package groovy.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.CharEncoding

/**
 * Created by I340951 on 8/16/2017.
 */
class SimpleJsonNodeDiff {

    static void main(String[] args) throws IOException {
        aValue_against_bValue()
        bValue_against_aValue()
    }

    static void compareJson(ObjectNode aValue, ObjectNode bValue, String key) {
        Iterator<String> i = aValue.fieldNames()

        while (i.hasNext()) {
            def field_name = i.next()
            compareJson(aValue.get(field_name), bValue.get(field_name), field_name)
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

    static StringBuffer result = new StringBuffer();
    static Boolean flag = true;
    static void compareJson(String str1, String str2, String key) {
        if (!str1.equals(str2)) {
            if(key == null) key = "."
            result.append("[${key}] - '${str1}' : '${str2}' , ")
            flag = false
        }
    }

    static void compareJson(ArrayNode aValue, ArrayNode bValue, String key) {
        Iterator i1= aValue.iterator()
        Iterator i2= bValue.iterator()
        while ( i1.hasNext()) {
            compareJson(i1.next(), i2.next(), key)
        }
    }

    static boolean compare(aValue, bValue) {
        result = new StringBuffer()
        flag = true
        ObjectMapper mapper = new ObjectMapper()
        def node = mapper.readTree(aValue)
        def node2 = mapper.readTree(bValue)
        compareJson(node, node2, null)
        def len = result.size()
        if(len > 3) result.delete(len-3, len)
        return flag
    }

    /**
     * debug code
     */
    static void aValue_against_bValue() throws IOException {
        String aValue = IOUtils.toString((new SimpleJsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8)
        String bValue = IOUtils.toString((new SimpleJsonNodeDiff()).getClass().getResourceAsStream("/json/TestJson2.json"), CharEncoding.UTF_8)
        println "aValue vs. bValue"
        println compare(aValue, bValue)
        println result
    }

    static void bValue_against_aValue() throws IOException {
        String aValue = IOUtils.toString((new SimpleJsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray.json"), CharEncoding.UTF_8)
        String bValue = IOUtils.toString((new SimpleJsonNodeDiff()).getClass().getResourceAsStream("/json/TestJsonArray2.json"), CharEncoding.UTF_8)

        println "bValue vs. aValue"
        println compare(bValue, aValue)
        println result
    }
}
