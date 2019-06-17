package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddNewContact extends TestBase{

  @Test
  public void testAddNewContact() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Dmitriy").withLastName("Naydyonov").withTitle("Mr.").withCompany("Test Company")
            .withAddress("Some test address").withHomePhone("123456789").withMobilePhone("987654321")
            .withEmail("dmitriy.naydenov@gmail.com").withEmail2("dmitriy.naydenov1@gmail.com")
            .withEmail3("dmitriy.naydenov2@gmail.com").withAddress2("Krakow").withBirth_day("6").withBirth_month("September").withBirth_year("1984");
    app.contact().create(contact);
    Contacts after = app.contact().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId()))));
  }
}
