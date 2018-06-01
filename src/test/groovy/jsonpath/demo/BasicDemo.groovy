package jsonpath.demo

import io.restassured.path.json.JsonPath

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class BasicDemo {
    static void demo() {
        def json = "{\"payload\":{\"currencyCode\":\"USD\",\"date\":1517471985522,\"orderId\":\"3815349312\",\"documentType\":\"SalesOrder\",\"items\":[{\"itemCode\":\"123456780\",\"quantity\":1,\"amount\":11.11,\"shipFrom\":null},{\"itemCode\":\"123456781\",\"quantity\":1,\"amount\":22.22,\"shipFrom\":null},{\"itemCode\":\"123456782\",\"quantity\":1,\"amount\":33.33,\"shipFrom\":null}],\"commit\":false,\"shipTo\":{\"streetNumber\":null,\"street\":null,\"line4\":null,\"line3\":null,\"line2\":\"16th Floor\",\"line1\":\"53 State St\",\"zip\":\"90099\",\"city\":\"Los Angels\",\"state\":\"CA\",\"country\":\"US\"}},\"tenant\":\"caas-integration-test\",\"id\":\"49bcbbad-0368-44d8-a356-a2ad2672b8ef\",\"respondTo\":\"tax.quote.response\"}"
        println json
        JsonPath json_path = JsonPath.from(json)
        println json_path.get('payload.orderId')
    }

    static void demo2() {
//        def json = "[{\\\"payload_bytes\\\": 624,\\\"payload\\\":\\\"{\\\"payload\\\":{\\\"currencyCode\\\":\\\"USD\\\",\\\"date\\\":1517471985522,\\\"orderId\\\":\\\"3815349312\\\",\\\"documentType\\\":\\\"SalesOrder\\\",\\\"items\\\":[{\\\"itemCode\\\":\\\"123456780\\\",\\\"quantity\\\":1,\\\"amount\\\":11.11,\\\"shipFrom\\\":null},{\\\"itemCode\\\":\\\"123456781\\\",\\\"quantity\\\":1,\\\"amount\\\":22.22,\\\"shipFrom\\\":null},{\\\"itemCode\\\":\\\"123456782\\\",\\\"quantity\\\":1,\\\"amount\\\":33.33,\\\"shipFrom\\\":null}],\\\"commit\\\":false,\\\"shipTo\\\":{\\\"streetNumber\\\":null,\\\"street\\\":null,\\\"line4\\\":null,\\\"line3\\\":null,\\\"line2\\\":\\\"16th Floor\\\",\\\"line1\\\":\\\"53 State St\\\",\\\"zip\\\":\\\"90099\\\",\\\"city\\\":\\\"Los Angels\\\",\\\"state\\\":\\\"CA\\\",\\\"country\\\":\\\"US\\\"}},\\\"tenant\\\":\\\"caas-integration-test\\\",\\\"id\\\":\\\"49bcbbad-0368-44d8-a356-a2ad2672b8ef\\\",\\\"respondTo\\\":\\\"tax.quote.response\\\"}\\\",\\\"payload_encoding\\\": \\\"string\\\"}]"
        def json = '[{"a":1, "b":1}]'
        println json
        println json.class
        JsonPath json_path = JsonPath.from(json)
        println json_path.class
        def member = json_path.getMap('[0]', String.class, Integer.class)
        println member.getClass()
        println member
        def member2 = json_path.getMap('[0]')
        println member2.getClass()
        println member2
    }

    static void main(String... args) {
        demo2()
    }
}
