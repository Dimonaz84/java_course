package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import pl.stqa.pft.mantis.model.UserData;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordReset() throws IOException {

        long now = System.currentTimeMillis();
        String username = app.getProperty("web.newUsername") + now;
        String password = app.getProperty("web.newUserPassword");
        String newPassword = app.getProperty("web.updatedPassword");
        String email = String.format("user%s@mail.com", now);
        if (app.db().getUsers().size() < 1) {      //IF THERE ARE NO USERS PRESENT, REGISTER NEW USER
            app.registration().start(username, email);
            List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);
            String confirmationLink = app.mail().findLinkFromMail(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(username, password));
            app.mail().restart();      //RESTARTING MAIL SERVER TO CLEAN MAILBOX FROM CONFIRMATION LINK
        }

        UserData user = app.db().getUsers().iterator().next();
        app.login();
        app.goTo().manageUsers();
        app.user().selectUser(user.getUserName());
        app.user().clickResetPassword();
        app.logout();
        List<MailMessage> mailMessages =  app.mail().waitForMail(1, 10000);
        String resetLink = app.mail().findLinkFromMail(mailMessages, user.getEmail());
        app.user().resetPassword(resetLink, newPassword);

        assertTrue(app.newSession().login(user.getUserName(), newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
