package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class AddNewContact extends TestBase{

  @Test
  public void testAddNewContact() {
    app.getContactHelper().goToContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Dmitriy", "Naydyonov", "Mr.", "Test Company", "Some test address", "123456789", "987654321", "dmitriy.naydenov@gmail.com", "dmitriy.naydenov1@gmail.com", "dmitriy.naydenov2@gmail.com", "Krakow", "6", "September", "1984"));
    app.getContactHelper().submitNewContact();
  }




}
