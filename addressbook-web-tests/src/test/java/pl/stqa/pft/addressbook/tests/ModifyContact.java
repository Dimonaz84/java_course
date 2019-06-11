package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ModifyContact extends TestBase{

    @Test
    public void testContactModification() {

        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Dmitriy", "Naydyonov", "Mr.", "Test Company",
                    "Some test address", "123456789", "987654321", "dmitriy.naydenov@gmail.com",
                    "dmitriy.naydenov1@gmail.com", "dmitriy.naydenov2@gmail.com", "Krakow", "6", "September", "1984"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().openContactForm(0);
        ContactData contact = new ContactData(before.get(0).getId(),"modifiedDmitriy", "modifiedNaydyonov", "modifiedMr.", "modifiedTest Company", "modified Some test address", "modified123456789",
                "modified987654321", "modifieddmitriy.naydenov@gmail.com", "modifieddmitriy.naydenov1@gmail.com", "modifieddmitriy.naydenov2@gmail.com", "modifiedKrakow", "7", "October", "1985");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().updateContact();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(0);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before,after);








    }
}
