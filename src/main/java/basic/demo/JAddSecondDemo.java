package basic.demo;

import java.util.Calendar;

/**
 * Created by I340951 on 8/1/2017.
 */
public class JAddSecondDemo {
    public static void main ( String[] args )
    {
        Calendar calendar = Calendar.getInstance();
        System.out.println (calendar.getTime ());
        calendar.add (Calendar.SECOND, 1);
        System.out.println (calendar.getTime ());
    }
}
