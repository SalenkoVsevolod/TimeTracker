package svs.timetracker.domain.model;

public class Project extends SpendingTimeCause {
    private String projectName;
    private String to;

    public Project(String to, String projectName) {
        this.to = to;
        setProjectName(projectName);
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
        cause = to + " " + projectName;
    }
}
