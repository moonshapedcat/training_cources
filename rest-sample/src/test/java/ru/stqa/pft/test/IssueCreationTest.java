package ru.stqa.pft.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.model.Issue;

import java.io.IOException;

import static org.testng.Assert.*;

public class IssueCreationTest extends TestBase {

    @Test
    public void IssueCreation() throws IOException {
        skipIfNotFixed(898);
        int oldCount = app.RestHelper().IssuesCount();
        Issue newIssue = new Issue().withSubject("test issue_!").withDescription("created by me").withStatus("Open");
        int newId = app.RestHelper().createIssue(newIssue);
        int newCount = app.RestHelper().IssuesCount();
        Issue issue = app.RestHelper().FindIssue(newId);
        assertEquals(newCount,oldCount+1);
        assertEquals(newIssue.withId(newId), issue);
    }
}
