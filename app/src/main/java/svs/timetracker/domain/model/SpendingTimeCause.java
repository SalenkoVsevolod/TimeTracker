package svs.timetracker.domain.model;

import android.arch.persistence.room.Ignore;

public class SpendingTimeCause {
    @Ignore
    protected String cause;

    @Ignore
    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return cause;
    }
}
