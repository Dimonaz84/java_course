package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ModifyContact extends TestBase{

    @Test
    public void testContactModification() {
        app.getContactHelper().openContactForm();
        app.getContactHelper().fillContactForm(new ContactData("modifiedDmitriy", "modifiedNaydyonov", "modifiedMr.", "modifiedTest Company", "modified Some test address", "modified123456789",
                "modified987654321", "modifieddmitriy.naydenov@gmail.com", "modifieddmitriy.naydenov1@gmail.com", "modifieddmitriy.naydenov2@gmail.com", "modifiedKrakow", "7", "October", "1985"));
        app.getContactHelper().updateContact();
    }
}
