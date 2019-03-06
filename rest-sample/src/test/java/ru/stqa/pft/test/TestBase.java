package ru.stqa.pft.test;

import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.appmanager.ApplicationManager;

import java.io.IOException;


public class TestBase {

  public static ApplicationManager app = new ApplicationManager();

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() throws Exception {

  }

  public ApplicationManager getApp() {
    return app;
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    return app.RestHelper().isIssueOpen(issueId);
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}
