package pl.stqa.pft.mantis.appmanager;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import pl.stqa.pft.mantis.model.BugifyIssue;
import java.io.IOException;
import java.util.Set;

public class RESTHelper {

    private ApplicationManager app;

    public RESTHelper(ApplicationManager app) {
        this.app = app;
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth(app.properties.getProperty("bugify.apiKey"), "");
    }

    public int createIssue(BugifyIssue newIssue) throws IOException {

        String json = getExecutor().execute(Request.Post(app.properties.getProperty("bugify.apiURL") + "/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                      new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<BugifyIssue> getIssues() throws IOException {

        String json = getExecutor().execute(Request.Get(app.properties.getProperty("bugify.apiURL") + "/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<BugifyIssue>>(){}.getType());
    }

    public String getIssueStatus(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get(app.properties.getProperty("bugify.apiURL") + "/issues/" + issueId + ".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json).getAsJsonObject().getAsJsonArray("issues").get(0);
        String status = parsed.getAsJsonObject().get("state_name").getAsString();
        return status;
    }
}
