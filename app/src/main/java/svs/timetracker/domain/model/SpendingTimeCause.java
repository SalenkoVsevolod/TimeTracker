package svs.timetracker.domain.model;

public class SpendingTimeCause {
    protected String cause;

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
