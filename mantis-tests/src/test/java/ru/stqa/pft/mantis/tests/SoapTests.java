package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.SoapHelper().getProjects();
        System.out.println(projects.size());
        for (Project proj : projects) {
            System.out.println(proj.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects = app.SoapHelper().getProjects();
        Issue issue = new Issue().withSummary("test issues").
                withDescription("issue description").withProject(projects.iterator().next());
        Issue created = app.SoapHelper().addIssue(issue);
        Assert.assertEquals(issue.getDescription(), created.getDescription());
    }
}
