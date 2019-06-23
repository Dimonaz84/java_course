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
           app.contact().create(new ContactData()
                   .withFirstName(app.properties.getProperty("contact.firstName"))
                   .withLastName(app.properties.getProperty("contact.lastName"))
                   .withTitle(app.properties.getProperty("contact.title"))
                   .withCompany(app.properties.getProperty("contact.company"))
                   .withAddress(app.properties.getProperty("contact.address"))
                   .withHomePhone(app.properties.getProperty("contact.homePhone"))
                   .withMobilePhone(app.properties.getProperty("contact.mobilePhone"))
                   .withWorkPhone(app.properties.getProperty("contact.workPhone"))
                   .withEmail(app.properties.getProperty("contact.email"))
                   .withEmail2(app.properties.getProperty("contact.email2"))
                   .withEmail3(app.properties.getProperty("contact.email3"))
                   .withAddress2(app.properties.getProperty("contact.address2"))
                   .withBirth_day(app.properties.getProperty("contact.birthDay"))
                   .withBirth_month(app.properties.getProperty("contact.birthMonth"))
                   .withBirth_year(app.properties.getProperty("contact.birthYear")));
       }
   }

    @Test
    public void testContactModification() {

        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstName(app.properties.getProperty("contact.modifiedFirstName"))
                .withLastName(app.properties.getProperty("contact.modifiedLastName"))
                .withTitle(app.properties.getProperty("contact.modifiedTitle"))
                .withCompany(app.properties.getProperty("contact.modifiedCompany"))
                .withAddress(app.properties.getProperty("contact.modifiedAddress"))
                .withHomePhone(app.properties.getProperty("contact.modifiedHomePhone"))
                .withMobilePhone(app.properties.getProperty("contact.modifiedMobilePhone"))
                .withWorkPhone(app.properties.getProperty("contact.modifiedWorkPhone"))
                .withEmail(app.properties.getProperty("contact.modifiedEmail"))
                .withEmail2(app.properties.getProperty("contact.modifiedEmail2"))
                .withEmail3(app.properties.getProperty("contact.modifiedEmail3"))
                .withAddress2(app.properties.getProperty("contact.modifiedAddress2"))
                .withBirth_day(app.properties.getProperty("contact.modifiedBirthDay"))
                .withBirth_month(app.properties.getProperty("contact.modifiedBirthMonth"))
                .withBirth_year(app.properties.getProperty("contact.modifiedBirthYear"));
        app.contact().modify(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
