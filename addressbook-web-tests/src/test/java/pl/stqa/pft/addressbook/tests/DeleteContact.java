package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

public class DeleteContact extends TestBase{

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
    public void testDeleteContact() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }
}
