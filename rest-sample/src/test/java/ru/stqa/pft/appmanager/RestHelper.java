package ru.stqa.pft.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import ru.stqa.pft.model.Issue;

import java.io.IOException;
import java.util.Set;

public class RestHelper {

    private final ApplicationManager app;

    public RestHelper(ApplicationManager app) {
        this.app = app;
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = FindIssue(issueId);
        return ! (issue.getState_name().equals("Resolved") || issue.getState_name().equals("Closed"));
    }


    private Executor getExecutor() {
        return Executor.newInstance().auth(app.getProperty("bugify.AuthKey"), "");
    }

    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post(app.getProperty("bugify.Url") + "/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                        new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public int IssuesCount() throws IOException {
        String json = getExecutor().execute(Request.Get(app.getProperty("bugify.Url") + "/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("total").getAsInt();
    }

    public Issue FindIssue(int id) throws IOException {
        String issueUrl = app.getProperty("bugify.Url") + String.format("/api/issues/%s.json", id);
        String json = getExecutor().execute(Request.Get(issueUrl)).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issuesJson = parsed.getAsJsonObject().get("issues");
        Set<Issue> issues = new Gson().fromJson(issuesJson, new TypeToken<Set<Issue>>(){}.getType());
        return issues.iterator().next();
    }
}
