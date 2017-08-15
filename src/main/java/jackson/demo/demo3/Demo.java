package jackson.demo.demo3;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;

/**
 * Created by I340951 on 8/14/2017.
 */
public class Demo {

//    public static <T> T json2Obj(String jsonStr, TypeReference<T> typeReference, String dateFormat)
//            throws Exception {
//        ObjectMapper objectMapper = getObjectMapper();
//        if (dateFormat != null && !dateFormat.trim().equals("")) {
//            objectMapper.setDateFormat(new SimpleDateFormat(dateFormat));
//        }
//
//        return objectMapper.readValue(jsonStr, typeReference);
//    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        //        当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能，
        //        因为，例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        String demoJsonString = IOUtils.toString(new Object().getClass().getResourceAsStream("/json/TestJson.json"),
                CharEncoding.UTF_8);

        //从json映射到java对象，得到country对象后就可以遍历查找,下面遍历部分内容，能说明问题就可以了
//        TestJson testJson = mapper.readValue("{\"a\":\"1\",\"b\":\"2\",\"c\":\"test\",\"d\":\"true\",\"e\":\"e\"}", TestJson.class);
        TestJson testJson = mapper.readValue(demoJsonString, TestJson.class);
        System.out.println("a:" + testJson.getA());
        System.out.println("b:" + testJson.getB());
        System.out.println("c:" + testJson.getC());
        System.out.println("d:" + testJson.getD());
    }
}
