package svs.timetracker.domain.model;

import svs.timetracker.domain.TimeUtils;

public abstract class PreviousWorkDayReport extends Report {
    @Override
    public void setCurrentDate(long millis) {
        super.setCurrentDate(millis);
        TimeUtils.setPreviousWorkDay(currentDate);
    }
}
