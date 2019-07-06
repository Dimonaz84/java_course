package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

public class DeleteContactFromGroup extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {

        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(app.properties.getProperty("group.Name")));
        }

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
                    .withPhoto(app.properties.getProperty("contact.photo")));
        }
    }

    @Test
    public void deleteContactFromGroup() {

        ContactData contact = app.db().contacts().iterator().next(); //SELECTING CONTACT
        GroupData group = app.db().groups().iterator().next(); //SELECTING GROUP

        if (!app.db().isContactAdded(contact,group)){      //CHECK IF CONTACT IS NOT ADDED TO GROUP, THEN ADD IT
            app.contact().addToGroup(contact, group);
            app.goTo().homePage();
        }

        app.contact().deleteFromGroup(contact.getId(), group.getId());
        Assert.assertFalse(app.db().isContactAdded(contact, group));
    }

}
