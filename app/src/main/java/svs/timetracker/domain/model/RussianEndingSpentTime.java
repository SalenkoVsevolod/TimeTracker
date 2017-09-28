package svs.timetracker.domain.model;

public class RussianEndingSpentTime extends SpentTime {
    private static final String HOUR_1 = "час";
    private static final String HOUR_2 = "часа";
    private static final String HOUR_3 = "часов";

    public RussianEndingSpentTime(final int hours) {
        super(hours, null);
        hoursText = createHoursText(hours);
    }

    private String createHoursText(final int hours) {
        final int lastNumber = hours % 10;
        if (lastNumber == 1) {
            return HOUR_1;
        }
        if (lastNumber > 1 && lastNumber < 5) {
            return HOUR_2;
        }
        return HOUR_3;
    }
}
