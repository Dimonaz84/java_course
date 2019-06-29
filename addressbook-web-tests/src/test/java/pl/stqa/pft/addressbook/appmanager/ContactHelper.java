package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper (WebDriver driver){
        super(driver);
    }

    public void submitNewContact() {
        click(By.xpath("//input[@value='Enter']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("address2"), contactData.getAddress2());
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText(contactData.getBirth_day());
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBirth_month());
        type(By.name("byear"), contactData.getBirth_year());
        attach(By.name("photo"), contactData.getPhoto());
        if (contactData.getGroups().size() > 0){
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        }
    }

    public void goToContactCreation() {
        click(By.xpath("//a[(.='add new')]"));
    }

    public void openContactFormById(int id) {
        driver.findElement(By.xpath("//input[@value='" + id + "']/following::img[@title='Edit'][1]")).click();
    }

    public void selectContactById(int id) {
        driver.findElement(By.xpath("//input[@value='" + id + "']")).click();
    }

    public void updateContact() {
        click(By.xpath("//input[@value='Update']"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        driver.switchTo().alert().accept();
    }

    public void create(ContactData contactData) {
        goToContactCreation();
        fillContactForm(contactData);
        submitNewContact();
    }

    public void modify(ContactData contact) {
        openContactFormById(contact.getId());
        fillContactForm(contact);
        updateContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name='selected[]']")).getAttribute("value"));
            String lastName = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[1]")).getText();
            String firstName = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[2]")).getText();
            String address = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[3]")).getText();
            String allEmails = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[4]")).getText();
            String allPhones = element.findElement(By.xpath(".//input[@name='selected[]']/following::td[5]")).getText();
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
            .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        openContactFormById(contact.getId());
        String firstName = driver.findElement(By.name("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.name("lastname")).getAttribute("value");
        String home = driver.findElement(By.name("home")).getAttribute("value");
        String mobile = driver.findElement(By.name("mobile")).getAttribute("value");
        String work = driver.findElement(By.name("work")).getAttribute("value");
        String email = driver.findElement(By.name("email")).getAttribute("value");
        String email2 = driver.findElement(By.name("email2")).getAttribute("value");
        String email3 = driver.findElement(By.name("email3")).getAttribute("value");
        String address = driver.findElement(By.name("address")).getAttribute("value");
        driver.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    public void waitForContactDeletion (){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.msgbox")));
        } catch (NoSuchElementException e){
            System.out.println("Contact was not deleted!");
            System.exit(1);
        }
    }
}
