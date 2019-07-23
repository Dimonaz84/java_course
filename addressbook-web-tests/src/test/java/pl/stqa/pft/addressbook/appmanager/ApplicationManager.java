package pl.stqa.pft.addressbook.appmanager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;
    public final Properties properties;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private String browser;
    private DBHelper dbHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws MalformedURLException {
        String target = System.getProperty("target", "local");
        try {
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        } catch (IOException e) {
            System.out.println("Configuration file not found!");
            System.exit(1);
        }

        dbHelper = new DBHelper();

        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.CHROME)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("marionette", false);
                driver = new FirefoxDriver(options);
            } else if (browser.equals(BrowserType.IEXPLORE)) {
                WebDriverManager.iedriver().setup();
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability("requireWindowFocus", true);
                driver = new InternetExplorerDriver(capabilities);
            } else if (browser.equals(BrowserType.SAFARI)) {
                driver = new SafariDriver();
            } else {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName(browser);
                driver = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
            }

            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseUrl"));
            sessionHelper = new SessionHelper(driver);
            groupHelper = new GroupHelper(driver);
            navigationHelper = new NavigationHelper(driver);
            contactHelper = new ContactHelper(driver);
            sessionHelper.login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    public void stop() {
        sessionHelper.logout();
        driver.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {return contactHelper;}

    public DBHelper db() { return dbHelper;}
}
