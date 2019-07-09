package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SessionHelper extends HelperBase{

    public SessionHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        type(By.name("username"), username);
        click(By.xpath("//input[@value='Login']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        type(By.name("password"), password);
        click(By.xpath("//input[@value='Login']"));
    }

    public void logout() {

        click(By.xpath("//span[@class='user-info']"));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=' Logout']"))).click();
    }
}
