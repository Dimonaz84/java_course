package pl.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();

        assertTrue(session.login(app.getProperty("web.username"), app.getProperty("web.password")));
        assertTrue(session.isLoggedInAs(app.getProperty("web.username")));
    }
}
