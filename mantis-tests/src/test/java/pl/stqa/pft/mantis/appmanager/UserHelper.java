package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserHelper extends HelperBase {

    public UserHelper (ApplicationManager app) {
        super(app);
    }

    public void selectUser(String username) {
        click(By.xpath("//a[text()='" + username + "']"));
    }

    public void clickResetPassword() {
        click(By.xpath("//input[@value='Reset Password']"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Manage Users']")));
    }

    public void resetPassword (String resetLink, String newPassword) {
        driver.get(resetLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.xpath("//span[text()='Update User']"));
    }
}
