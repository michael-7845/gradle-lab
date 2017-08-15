package jackson.demo.demo5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by I340951 on 8/15/2017.
 */
public class JsonNodeComparor {
    public static void main(String[] args) throws IOException {
        String json = "", json2 = "";
        json = IOUtils.toString((new JsonNodeComparor()).getClass().getResourceAsStream("/json/TestJson.json"), CharEncoding.UTF_8);
        json2 = IOUtils.toString((new JsonNodeComparor()).getClass().getResourceAsStream("/json/TestJson2.json"), CharEncoding.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(json);
        JsonNode node2 = mapper.readTree(json2);
//        compareJson(node, node2,null);
    }
//    public static void compareJson(JSONObject json1, JSONObject json2, String key) {
//        Iterator<String> i = json1.keys();
//        while (i.hasNext()) {
//            key = i.next();
//            compareJson(json1.get(key), json2.get(key),key);
//        }
//    }
//    public static void compareJson(Object json1,Object json2,String key) {
//        if ( json1 instanceof JSONObject ) {
//            compareJson((JSONObject) json1 ,(JSONObject) json2,key);
//        }else if ( json1 instanceof JSONArray ) {
//            compareJson((JSONArray) json1 ,(JSONArray) json2,key);
//        }else if(json1 instanceof String ){
//            compareJson((String) json1 ,(String) json2,key);
//        }else {
//            compareJson(json1.toString(), json2.toString(), key);
//        }
//    }
//
//    public static void compareJson(String str1,String str2,String key) {
//        if (!str1.equals(str2)) {
//            System.out.println("key:"+key+",json1:"+ str1 +",json2:"+str2);
//        }
//    }
//    public static void compareJson(JSONArray json1,JSONArray json2,String key) {
//        Iterator i1= json1.iterator();
//        Iterator i2= json2.iterator();
//        while ( i1.hasNext()) {
//            compareJson(i1.next(), i2.next(),key);
//        }
//    }
//    public static JSONObject getJson(String url) {
//        try {
//            URL url1 = new URL(url);
//            InputStream in = url1.openStream();
//            OutputStream out = new ByteArrayOutputStream();
//            IOUtils.copy(in, out);
//            String aa = out.toString();
//            in.close();
//            out.close();
//            return JSONObject.fromObject(aa);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new JSONObject();
//    }
}
