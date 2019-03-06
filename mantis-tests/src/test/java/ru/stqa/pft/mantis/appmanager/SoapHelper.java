package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper extends HelperBase {

    public SoapHelper(ApplicationManager app) {
        super(app);
    }

    public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
        MantisConnectPortType mantis = getMantisConnector();
        ProjectData[] projects = mantis.mc_projects_get_user_accessible("administrator", "root");
        return Arrays.asList(projects).stream().map((p) -> new Project()
                .withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
    }

    private MantisConnectPortType getMantisConnector() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
                .getMantisConnectPort(new URL(app.getProperty("web.mantisWsdl")));
    }


    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantis = getMantisConnector();
        String[] categories = mantis.mc_project_get_categories("administrator", "root", BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        issueData.setCategory(categories[0]);
        BigInteger issueId = mantis.mc_issue_add("administrator", "root", issueData);
        IssueData createdIssueData = mantis.mc_issue_get("administrator", "root", issueId);
        return new Issue().withId(createdIssueData.getId().intValue()).withSummary(createdIssueData.getSummary())
                .withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mantis = getMantisConnector();
        IssueData issueData = mantis.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        String status = issueData.getStatus().getName();
        return ! (status.equals("resolved") || status.equals("closed"));
    }
}
