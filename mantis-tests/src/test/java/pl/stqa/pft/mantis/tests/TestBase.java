package pl.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.mantis.appmanager.ApplicationManager;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
        //app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    boolean isIssueOpen(BigInteger issueId) throws ServiceException, MalformedURLException {
        String status = "";
        try {
            status = app.soap().getIssueStatus(issueId);
        } catch (RemoteException e) {
            System.out.println("No issue with id " + issueId + " found!");
        }
        if (status.equals("resolved") || status.equals("closed")) {
            return false;
        } return true;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(BigInteger.valueOf(issueId))) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
