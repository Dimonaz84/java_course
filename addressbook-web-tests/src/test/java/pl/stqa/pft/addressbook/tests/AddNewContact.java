package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewContact extends TestBase{

  @Test
  public void testAddNewContact() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Dmitriy", "Naydyonov", "Mr.", "Test Company",
            "Some test address", "123456789", "987654321", "dmitriy.naydenov@gmail.com",
            "dmitriy.naydenov1@gmail.com", "dmitriy.naydenov2@gmail.com", "Krakow", "6", "September", "1984");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    Assert.assertEquals(before, after);
  }
}
