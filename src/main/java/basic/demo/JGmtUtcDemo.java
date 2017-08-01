package basic.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by I340951 on 8/1/2017.
 * http://blog.csdn.net/wsscy2004/article/details/41040853
 */
public class JGmtUtcDemo {
    //T代表后面跟着时间，Z代表UTC统一时间
    String tpTme = "2014-11-11T14:00:00+0800";
    String pmTime = "2014-11-07T14:00:00Z";

    public void testTPTime() throws Exception {
//2014-11-11T14:00:00+08:00
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String time = format.format(new Date());
        System.out.println(time);

    }

    public void testPMTime() throws Exception {
//2014-11-07T14:00:00Z
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String time = format.format(new Date());
        System.out.println(time);
    }

    //转换回来
    public void testParsePMTime() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date time = df.parse(pmTime);
        System.out.println(time);
    }

    //转换回来
    public void testParseTPTime() throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date time = df.parse(tpTme);
        System.out.println(time);
    }
}
