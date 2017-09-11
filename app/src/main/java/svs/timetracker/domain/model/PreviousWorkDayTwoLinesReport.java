package svs.timetracker.domain.model;

public class PreviousWorkDayTwoLinesReport extends PreviousWorkDayReport {

    @Override
    protected String getReportPattern() {
        return "%s\n%s - %s %s";
    }

    @Override
    protected boolean shouldShowYear() {
        return false;
    }
}
