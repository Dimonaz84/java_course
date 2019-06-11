package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContact extends TestBase{

    @Test
    public void testDeleteContact() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Dmitriy", "Naydyonov", "Mr.", "Test Company",
                    "Some test address", "123456789", "987654321", "dmitriy.naydenov@gmail.com",
                    "dmitriy.naydenov1@gmail.com", "dmitriy.naydenov2@gmail.com", "Krakow", "6", "September", "1984"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(0);
        Assert.assertEquals(before, after);
    }
}
