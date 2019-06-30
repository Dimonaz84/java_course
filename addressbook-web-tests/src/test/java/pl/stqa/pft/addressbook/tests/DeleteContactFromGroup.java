package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

public class DeleteContactFromGroup extends TestBase {

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
