package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String username = app.getProperty("web.newUsername") + now;
        String password = app.getProperty("web.newUserPassword");
        String email = String.format("user%s@mail.com", now);
//      app.james().createUser(username, password);
        app.registration().start(username, email);
        List<MailMessage> mailMessages =  app.mail().waitForMail(2, 10000);
//      List<MailMessage> mailMessages = app.james().waitForMail(username, password, 10000);
        String confirmationLink = app.mail().findLinkFromMail(mailMessages, email);
        app.registration().finish(confirmationLink, password);
        assertTrue(app.newSession().login(username, password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
