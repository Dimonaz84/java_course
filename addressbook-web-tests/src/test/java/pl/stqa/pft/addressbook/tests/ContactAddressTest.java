package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {
    @BeforeMethod
    public void checkPreconditions() {
        if(app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstName("Dmitriy").withLastName("Naydyonov").withTitle("Mr.").withCompany("Test Company")
                    .withAddress("Some test address").withHomePhone("123456789").withMobilePhone("987654321").withWorkPhone("089745623")
                    .withEmail("dmitriy.naydenov@gmail.com").withEmail2("dmitriy.naydenov1@gmail.com")
                    .withEmail3("dmitriy.naydenov2@gmail.com").withAddress2("Krakow").withBirth_day("6").withBirth_month("September").withBirth_year("1984"));
        }
    }

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
