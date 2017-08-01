package basic.demo;

/**
 * Created by I340951 on 8/1/2017.
 * http://www.cnblogs.com/wshlf/p/6241710.html
 */
import java.util.Calendar;
import java.util.Date;

public class CalendarDemo {
    public static void main(String[] args) {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        System.out.println("现在时间是："+new Date());
        String year=String.valueOf(calendar.get(Calendar.YEAR));
        String month=String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String week=String.valueOf(calendar.get(Calendar.DAY_OF_WEEK)-1);
        System.out.println("现在时间是："+year+"年"+month+"月"+day+"日，星期"+week);
        long year2009=calendar.getTimeInMillis();
        calendar.set(1989,9,26);//这里与真实的月份之间相差1
        long year1989=calendar.getTimeInMillis();
        long days=(year2009-year1989)/(1000*60*60*24);
        System.out.println("今天和1989年10月26日相隔"+days+"天，"+"也就是说我在这个美丽的星球上已经幸福的生活了"+days+"天。");

    }
}
