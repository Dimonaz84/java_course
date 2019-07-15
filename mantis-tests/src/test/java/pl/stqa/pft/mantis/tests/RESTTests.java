package pl.stqa.pft.mantis.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.BugifyIssue;
import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RESTTests extends TestBase {

    @BeforeTest
    public void checkBugStatus() throws IOException {
        skipIfBugifyNotFixed(Integer.parseInt(app.properties.getProperty("bugify.issueId")));
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<BugifyIssue> oldIssues = app.rest().getIssues();
        BugifyIssue newIssue = new BugifyIssue().withSubject("Test subject").withDescription("Test description");
        int issueId = app.rest().createIssue(newIssue);
        Set<BugifyIssue> newIssues = app.rest().getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }
}
