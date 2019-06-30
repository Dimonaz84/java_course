package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

public class AddContactToGroup extends TestBase {

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
    public void addContactToGroup() {

        ContactData contact = app.db().contacts().iterator().next(); //SELECTING CONTACT

        Groups groups = app.db().groups();
        GroupData freeGroup = app.db().findFreeGroup(groups, contact.getId()); //GET ID OF A GROUP THAT CURRENT CONTACT IS NOT YET ASSIGNED TO

        if (freeGroup == null){                                   //CHECK IF CONTACT IS ALREADY ADDED TO ALL GROUPS
            GroupData extraGroup = new GroupData().withName("Extra group"); //ADD NEW GROUP
            app.goTo().groupPage();
            app.group().create(extraGroup);
            extraGroup.withId(app.db().getNewGroupId());      //CHANGING GROUP ID TO ID OF NEWLY CREATED GROUP
            contact.inGroup(extraGroup);                      //ADD CONTACT TO NEWLY CREATED GROUP
            app.goTo().homePage();
            app.contact().addToGroup(contact, extraGroup);   //CHECK CONTACT IS ADDED

            Assert.assertTrue(app.db().isContactAdded(contact,extraGroup));

        } else {
            contact.inGroup(freeGroup);
            app.contact().addToGroup(contact, freeGroup);                    //ADD CONTACT TO FREE GROUP
            Assert.assertTrue(app.db().isContactAdded(contact,freeGroup));   //CHECK CONTACT IS ADDED
        }
    }
}
