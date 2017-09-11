package svs.timetracker.domain.model;

import java.util.Calendar;

import svs.timetracker.data.StringConstants;
import svs.timetracker.utils.StringUtils;

public abstract class Report {
    protected final Calendar currentDate = Calendar.getInstance();
    protected SpendingTimeCause spendingTimeCause;
    protected SpentTime spentTime;
    protected Greeting greeting;

    public SpendingTimeCause getSpendingTimeCause() {
        return spendingTimeCause;
    }

    public void setSpendingTimeCause(SpendingTimeCause spendingTimeCause) {
        this.spendingTimeCause = spendingTimeCause;
    }

    public Calendar getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(long millis) {
        currentDate.setTimeInMillis(millis);
    }

    public SpentTime getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(SpentTime spentTime) {
        this.spentTime = spentTime;
    }

    public Greeting getGreeting() {
        return greeting;
    }

    public void setGreeting(Greeting greeting) {
        this.greeting = greeting;
    }

    @Override
    public String toString() {
        return String.format(getReportPattern(),
                greeting.toString(),
                StringUtils.formatDate(currentDate, StringConstants.DATE_DIVIDER, shouldShowYear()),
                spentTime.toString(), spendingTimeCause == null ? "" : spendingTimeCause.toString());
    }

    protected abstract String getReportPattern();

    protected abstract boolean shouldShowYear();
}
