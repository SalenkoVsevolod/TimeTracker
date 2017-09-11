package svs.timetracker.domain;

import java.util.Calendar;

public final class TimeUtils {
    public static void decreaseDayOfMonth(Calendar calendar, int delta) {
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - delta);
    }

    public static void setPreviousWorkDay(Calendar calendar) {
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            decreaseDayOfMonth(calendar, 3);
        } else {
            decreaseDayOfMonth(calendar, 1);
        }
    }
}
