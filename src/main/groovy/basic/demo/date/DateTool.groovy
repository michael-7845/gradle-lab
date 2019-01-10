package basic.demo.date

import java.sql.Timestamp
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class DateTool {
    // date format
    public static final String DATE_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS"
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    public static final String DATE_FORMAT_WITHOUT_MS = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    public static final String DATE_FORMAT_WITHOUT_S_MS = "yyyy-MM-dd'T'HH:mm'Z'"

    ///////////////////////////////////////////////////
    // tool
    static String remove_ms(time) {
        def d = Date.parse(DATE_FORMAT, time)
        def sdf = new SimpleDateFormat(DATE_FORMAT_WITHOUT_MS)
        return sdf.format(d)
    }

    static String remove_s_ms(time) {
        def d = Date.parse(DATE_FORMAT, time)
        def sdf = new SimpleDateFormat(DATE_FORMAT_WITHOUT_S_MS)
        return sdf.format(d)
    }

    static String add_1_second(time) {
        def d = Date.parse(DATE_FORMAT_WITHOUT_MS, time)
        def c = d.toCalendar()
        c.add(Calendar.SECOND, 1)
        d = c.getTime()
        def sdf = new SimpleDateFormat(DATE_FORMAT_WITHOUT_MS)
        return sdf.format(d)
    }

    static String deduct_1_second(time) {
        def d = Date.parse(DATE_FORMAT_WITHOUT_MS, time)
        def c = d.toCalendar()
        c.add(Calendar.SECOND, -1)
        d = c.getTime()
        def sdf = new SimpleDateFormat(DATE_FORMAT_WITHOUT_MS)
        return sdf.format(d)
    }

    /////////////////////////////////////////////////////////
    // conversion
    static Date string_to_date(String time, String format = DATE_FORMAT) {
        Date.parse(format, time)
    }

    // use date.toTimestamp()
    @Deprecated
    static Timestamp date_to_timestamp(Date date) {
        if(date == null) {
            return new Timestamp((new Date()).getTime())
        }
        return new Timestamp(date.getTime())
    }

    static Date to_current_timezone_date(String time, String format = DATE_FORMAT){
        TimeZone timezone = TimeZone.getDefault()
        TimeZone utc_timezone = TimeZone.getTimeZone("UTC")
        def time_offset = timezone.getRawOffset() - utc_timezone.getRawOffset()
        DateFormat utc_format = new SimpleDateFormat(format)
        def utc_date = utc_format.parse(time)
        def date = new Date(utc_date.getTime() + time_offset)
        return date
    }

    ////////////////////////////////////////////////////////
    // IOS8601UTC
    static String toISO8601UTC(Date date, String format = DATE_FORMAT) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(tz);
        return df.format(date);
    }

    static Date fromISO8601UTC(String dateStr, String format = DATE_FORMAT) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat(format);
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void demo_transit() {
        // T means following time, Z means UTC
        // format time
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        String time = sdf.format(new Date());
        System.out.println(time);

        //parse time 2016-01-05T15:09:54Z
        Date date = sdf.parse(time);
        System.out.println(date);
    }

    private static void demo(t) {
        println "time: ${t}"
        Date d = fromISO8601UTC(t), d2 = string_to_date(t)
        println '============ d'
        println d
        println d.toTimestamp().getTime()
        println '============ d2'
        println d2
        println d2.toTimestamp().getTime()
        println '============ tool'
        def s = remove_ms(t)
        println s
        println add_1_second(s)
        println deduct_1_second(s)
    }

    private static void demo1() {
        def s = '2017-08-01T02:02:44.371Z'
        demo(s)
        s = '2017-08-01T02:02:00.371Z'
        demo(s)
        s = '2017-08-01T02:00:00.371Z'
        demo(s)
        s = '2017-08-01T00:00:00.371Z'
        demo(s)
        s = '2017-08-01T02:02:59.371Z'
        demo(s)
        s = '2017-08-01T02:59:59.371Z'
        demo(s)
        s = '2017-08-01T23:59:59.371Z'
        demo(s)
    }

    private static void demo2() {
        def time = '2017-11-03T02:50:24.802Z'
        Date date = string_to_date(time)
        Timestamp update_at = date_to_timestamp(date)
        println date
        println date.toTimestamp()
        println date.toTimestamp().time
        println update_at
        println update_at.time
    }

    static void lab1() {
        String dateString = "Thu Sep 07 2017 00:00:00 GMT+0800 (中国标准时间) 00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss 'GMT'Z", Locale.ENGLISH);
        Date dd = sdf.parse(dateString); //将字符串改为date的格式
        String resDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dd);
        System.out.println(resDate);

        String s = 'Mon, 27 Aug 2018 06:54:03 GMT'
        sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
        dd = sdf.parse(s);
        resDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dd);
        System.out.println(resDate);

//        def d = Date.parse("yyyy-MM-dd'T'HH:mm:ss", dd)
        def c = dd.toCalendar()
        c.add(Calendar.SECOND, 10)
        def d = c.getTime()
        def sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        println sdf2.format(d)

        def c2 = dd.toCalendar()
        c2.add(Calendar.SECOND, -10)
        def d2 = c2.getTime()
        println sdf2.format(d2)
    }

    static void main(String... args) {
        lab1()
    }
}
