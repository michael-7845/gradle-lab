package basic.demo.date

import java.text.SimpleDateFormat

/**
 * Created by I340951 on 8/1/2017.
 */
class DateDemo {
    Date createdAt
    static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

    static void demo1() {
        def demo = new DateDemo()
        demo.getTimeStamp()
    }

    /**
     * 获取时间戳
     * 输出结果:1438692801766
     */
    public void getTimeStamp() {
        Date date = new Date();
        long times = date.getTime();
        System.out.println(times);

        //第二种方法：
        new Date().getTime();
    }

    /**
     * 获取格式化的时间
     * 输出格式：2015-08-04 20:55:35
     */
    public void getFormatDate(){
        Date date = new Date();
        long times = date.getTime();//时间戳
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        System.out.println(dateString);
    }

    /**
     * 将时间戳转化为标准时间
     * 输出：Tue Oct 07 12:04:36 CST 2014
     */
    public void timestampToDate(){
        long times = 1412654676572L;
        Date date = new Date(times);
        System.out.println(date);
    }

    static void main(args) {
        demo1()
    }
}
