package pl.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import pl.stqa.pft.mantis.model.Issue;
import pl.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
    private final ApplicationManager app;

    public SoapHelper(ApplicationManager app) {
        this.app = app;
    }

    private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator().getMantisConnectPort(new URL(app.properties.getProperty("soap.url")));
    }

    public Set<Project> getProjects() throws RemoteException, MalformedURLException, ServiceException {
        MantisConnectPortType mantisConnectPort = getMantisConnect();
        ProjectData[] projects = mantisConnectPort.mc_projects_get_user_accessible(app.properties.getProperty("web.username"), app.properties.getProperty("web.password"));
        return Arrays.asList(projects).stream().map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantisConnectPort = getMantisConnect();
        String[] categories = mantisConnectPort.mc_project_get_categories(app.properties.getProperty("web.username"), app.properties.getProperty("web.password"), BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mantisConnectPort.mc_issue_add(app.properties.getProperty("web.username"), app.properties.getProperty("web.password"), issueData);
        IssueData createdIssueData = mantisConnectPort.mc_issue_get(app.properties.getProperty("web.username"), app.properties.getProperty("web.password"), issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withDescription(createdIssueData.getDescription())
                .withSummary(createdIssueData.getSummary())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                .withName(createdIssueData.getProject().getName()));
    }
}
