package jackson.demo.demo1;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by I340951 on 8/14/2017.
 */
public class StreamingDemo {

    // Data Binding - parse
    public static void demo1() throws IOException {
        // 从JsonFactory获得一个JsonParser实例
        JsonFactory factory = new JsonFactory();
        //createParser有很多种重载，具体可以看API
        JsonParser parser = factory.createParser("{\"title\":\"Free Music Archive - Albums\",\"message\":\"test\",\"errors\":[\"invalid or disabled api_key\"],\"dataset\":[{\"id\":\"1\"},{\"id\":\"2\"}]}");

        // 解析符号直到字符串结尾
        while (!parser.isClosed()) {
            // 如果有必要的话，这个方法会沿着流前进直到足以确下一个JsonToken的类型
            JsonToken token = parser.nextToken();
            // 如果是最后一个JsonToken，那么就结束了
            if (token == null)
                break;
            //如果JsonToken是JsonToken.FIELD_NAME类型并且当前的name是dataset
            if (JsonToken.FIELD_NAME.equals(token) && "dataset".equals(parser.getCurrentName())) {
                // 开始解析dateset，第一个JsonToken必须是JsonToken.START_ARRAY"["
                token = parser.nextToken();
                if (!JsonToken.START_ARRAY.equals(token)) {
                    break;
                }
                // 数组的每个元素都是对象，因此下一个JsonToken是JsonToken.START_OBJECT"{"
                token = parser.nextToken();
                if (!JsonToken.START_OBJECT.equals(token)) {
                    break;
                }
                // 输出id字段的值
                while (true) {
                    token = parser.nextToken();
                    if (token == null)
                        break;
                    if (JsonToken.FIELD_NAME.equals(token) && "id".equals(parser.getCurrentName())) {
                        token = parser.nextToken();
                        System.out.println(parser.getText());
                    }
                }
            }
        }
    }

    // Data Binding - generate
    public static void demo2() throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = factory.createGenerator(new BufferedOutputStream(System.out));

        generator.writeStartObject();//{
        generator.writeFieldName("title");
        generator.writeString("Free Music Archive - Albums");
        generator.writeFieldName("dataset");
        generator.writeStartArray();//[
        generator.writeStartObject();//{
        generator.writeStringField("album_title", "A.B.A.Y.A.M");
        generator.writeEndObject();//}
        generator.writeEndArray();//]
        generator.writeEndObject();//}
        //输出{"title":"Free Music Archive - Albums","dataset":[{"album_title":"A.B.A.Y.A.M"}]}

        generator.close();
    }

    // Tree Mode - generate
    public static void demo3() throws IOException {
        //创建一个提供所有节点的JsonNodeFactory,false表示不创建DecimalNode
        JsonNodeFactory factory = new JsonNodeFactory(false);

        // 创建JsonFactory，使用Streaming API的方式输出到控制台
        JsonFactory jsonFactory = new JsonFactory();
        JsonGenerator generator = jsonFactory.createGenerator(System.out);
        ObjectMapper mapper = new ObjectMapper();

        //创建节点和数据,一个ObjectNode代表一个节点对象
        ObjectNode node1 = factory.objectNode();
        ObjectNode node2 = factory.objectNode();
        node1.put("A", "a");
        node1.put("B", "b");
        node2.set("C", node1);

        // 根节点
        ObjectNode root = factory.objectNode();
        root.put("root", "root");
        root.set("children", node2);
        mapper.writeTree(generator, root);
        //输出{"root":"root","children":{"C":{"A":"a","B":"b"}}}
    }

    // Tree Mode - parse
    public static void demo4() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // 读取json，将整个json作为根节点
        JsonNode node = mapper.readTree("{\"root\":\"root\",\"children\":{\"C\":{\"A\":\"a\",\"B\":\"b\"}}}");
        // 看看根节点的类型
        System.out.println("node JsonNodeType:" + node.getNodeType());//OBJECT
        // 是不是一个ContainerNode（array and object nodes）
        System.out.println("node is containerNode ? " + node.isContainerNode());//true
        // 得到所有node节点的直接子节点名称
        Iterator<String> fieldNames = node.fieldNames();
        while (fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            System.out.println(fieldName + " ");//root children
        }
        System.out.println(node.get("root").isContainerNode());//false
    }


    public static void main(String[] args) throws Exception {
        demo4();
    }

}
