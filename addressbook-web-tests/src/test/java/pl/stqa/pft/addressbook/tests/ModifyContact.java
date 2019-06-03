package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ModifyContact extends TestBase{

    @Test
    public void testContactModification() {

        if(!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("Dmitriy", "Naydyonov", "Mr.", "Test Company",
                    "Some test address", "123456789", "987654321", "dmitriy.naydenov@gmail.com",
                    "dmitriy.naydenov1@gmail.com", "dmitriy.naydenov2@gmail.com", "Krakow", "6", "September", "1984"));
        }
        app.getContactHelper().openContactForm();
        app.getContactHelper().fillContactForm(new ContactData("modifiedDmitriy", "modifiedNaydyonov", "modifiedMr.", "modifiedTest Company", "modified Some test address", "modified123456789",
                "modified987654321", "modifieddmitriy.naydenov@gmail.com", "modifieddmitriy.naydenov1@gmail.com", "modifieddmitriy.naydenov2@gmail.com", "modifiedKrakow", "7", "October", "1985"));
        app.getContactHelper().updateContact();
    }
}
