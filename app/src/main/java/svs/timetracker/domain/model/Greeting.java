package svs.timetracker.domain.model;

public class Greeting {
    private String suffix;
    private String greeting;
    private String appeal;

    public Greeting(String greeting, String appeal, String suffix) {
        this.greeting = greeting;
        this.appeal = appeal;
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return greeting + (appeal == null ? "" : ", " + appeal) + suffix;
    }
}
