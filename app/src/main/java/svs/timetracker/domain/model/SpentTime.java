package svs.timetracker.domain.model;

public class SpentTime {
    private long spentMillis;
    private boolean useHoursWithMinutes;
    private String hoursText;
    private String minutesText;

    public SpentTime(long spentMillis, String hoursText) {
        this.spentMillis = spentMillis;
        this.hoursText = hoursText;
        useHoursWithMinutes = false;
    }

    public SpentTime(int hours, String hoursText) {
        this((long) (hours * 1000 * 3600), hoursText);
    }

    public SpentTime(long spentMillis, String hoursText, String minutesText) {
        this(spentMillis, hoursText);
        this.minutesText = minutesText;
        useHoursWithMinutes = true;
    }

    private int getSeconds() {
        return (int) spentMillis / 1000;
    }

    private int getMinutes() {
        return getSeconds() / 60;
    }

    private int getHours() {
        return getMinutes() / 60;
    }

    @Override
    public String toString() {
        String res = getHours() + " " + hoursText;
        if (useHoursWithMinutes) {
            res += " " + minutesText + " " + getMinutes();
        }
        return res;
    }
}
