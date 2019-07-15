package pl.stqa.pft.mantis.model;

import java.util.Objects;

public class BugifyIssue {

    private int id;
    private String subject;
    private String description;

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public BugifyIssue withId(int id) {
        this.id = id;
        return this;
    }

    public BugifyIssue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public BugifyIssue withDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BugifyIssue that = (BugifyIssue) o;
        return id == that.id &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subject, description);
    }
}
