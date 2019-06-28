package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

public class DeleteContact extends TestBase{

    @BeforeMethod
    public void checkPreconditions() {
        if(app.db().contacts().size() == 0) {
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
                    .withBirth_year(app.properties.getProperty("contact.birthYear"))
                    .withPhoto(app.properties.getProperty("contact.photo"))
            );
        }
    }

    @Test
    public void testDeleteContact() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Thread.sleep(1000); //wait until deprecated field is updated
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
