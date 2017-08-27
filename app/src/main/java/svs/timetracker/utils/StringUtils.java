package svs.timetracker.utils;

import java.util.Calendar;

public final class StringUtils {
    public static String formatNumber(int number) {
        if (number < 10) {
            return "0" + number;
        } else {
            return "" + number;
        }
    }

    public static String formatDate(Calendar calendar, String divider, boolean showYear) {
        String dayAndMonth = formatNumber(calendar.get(Calendar.DAY_OF_MONTH))
                + divider
                + formatNumber(calendar.get(Calendar.MONTH) + 1);
        if (showYear) {
            dayAndMonth += divider + formatNumber(calendar.get(Calendar.YEAR));
        }
        return dayAndMonth;
    }
}
