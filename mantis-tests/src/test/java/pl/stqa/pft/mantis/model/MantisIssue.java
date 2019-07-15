package pl.stqa.pft.mantis.model;

public class MantisIssue {

    private int id;
    private String summary;
    private String description;
    private Project project;

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    public Project getProject() {
        return project;
    }

    public MantisIssue withId(int id) {
        this.id = id;
        return this;
    }

    public MantisIssue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public MantisIssue withDescription(String description) {
        this.description = description;
        return this;
    }

    public MantisIssue withProject(Project project) {
        this.project = project;
        return this;
    }
}
