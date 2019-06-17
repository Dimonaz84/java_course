package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModifyContact extends TestBase{

   @BeforeMethod
   public void checkPreconditions() {
       if(app.contact().all().size() == 0) {
           app.contact().create(new ContactData().withFirstName("Dmitriy").withLastName("Naydyonov").withTitle("Mr.").withCompany("Test Company")
                   .withAddress("Some test address").withHomePhone("123456789").withMobilePhone("987654321")
                   .withEmail("dmitriy.naydenov@gmail.com").withEmail2("dmitriy.naydenov1@gmail.com")
                   .withEmail3("dmitriy.naydenov2@gmail.com").withAddress2("Krakow").withBirth_day("6").withBirth_month("September").withBirth_year("1984"));
       }
   }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("modifiedDmitriy").withLastName("modifiedNaydyonov").withTitle("modifiedMr.").withCompany("modifiedTest Company")
                .withAddress("modifiedSome test address").withHomePhone("modified123456789").withMobilePhone("modified987654321")
                .withEmail("modifieddmitriy.naydenov@gmail.com").withEmail2("modifieddmitriy.naydenov1@gmail.com")
                .withEmail3("modifieddmitriy.naydenov2@gmail.com").withAddress2("modifiedKrakow").withBirth_day("7").withBirth_month("October").withBirth_year("1985");
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
