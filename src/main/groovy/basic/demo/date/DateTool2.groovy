package basic.demo.date

import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

/**
 * Author: Michael Yu
 * Dept: CAAS
 * Team: Mooncake
 */
class DateTool2 {
    public static final String DATE_FORMAT= "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_FORMAT).withLocale(Locale.UK).withZone(ZoneOffset.UTC);

    /**
     * format date in "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" pattern.
     * @param date Date to be formatted
     * @return format string
     */
    public static String format(final Date date) {
        return DATE_TIME_FORMATTER.format(date.toInstant());
    }

    static void lab() {
        def TODAY = format(new Date())
        def YESTERDAY = format(new Date() - 1)
        def TOMORROW = format(new Date() + 1)
        println TODAY
        println YESTERDAY
        println TOMORROW
    }

    static void main(String... args) {
        lab()
    }
}
