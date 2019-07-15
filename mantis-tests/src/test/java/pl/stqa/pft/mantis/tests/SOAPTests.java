package pl.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MantisIssue;
import pl.stqa.pft.mantis.model.Project;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SOAPTests extends TestBase{

    @BeforeTest
    public void checkBugStatus() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(Integer.parseInt(app.properties.getProperty("soap.issueId")));
    }

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.soap().getProjects();
        MantisIssue mantisIssue = new MantisIssue().withSummary("Test MantisIssue").withDescription("Test Description").withProject(projects.iterator().next());
        MantisIssue created = app.soap().addIssue(mantisIssue);
        Assert.assertEquals(mantisIssue.getSummary(), created.getSummary());
    }
}
