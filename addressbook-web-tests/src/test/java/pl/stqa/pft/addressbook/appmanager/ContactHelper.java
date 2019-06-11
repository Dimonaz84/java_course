package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper (WebDriver driver){
        super(driver);
    }

    public void submitNewContact() {
        click(By.xpath("//input[@value='Enter']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address2"), contactData.getAddress2());
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText(contactData.getBirth_day());
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBirth_month());
        type(By.name("byear"), contactData.getBirth_year());
    }

    public void goToContactCreation() {
        click(By.xpath("//a[(.='add new')]"));
    }

    public void openContactForm(int index) {
        driver.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
    }

    public void selectContact(int index) {
        driver.findElements(By.name("selected[]")).get(index).click();
    }

    public void updateContact() {
        click(By.xpath("//input[@value='Update']"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
    }


    public void createContact(ContactData contactData) {
        goToContactCreation();
        fillContactForm(contactData);
        submitNewContact();

    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tr[@name='entry']"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("value"));
            String lastName = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[1]")).getText();
            String firstName = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[2]")).getText();
            ContactData contact = new ContactData(id, firstName, lastName);
            contacts.add(contact);
        }
        return contacts;
    }
}
