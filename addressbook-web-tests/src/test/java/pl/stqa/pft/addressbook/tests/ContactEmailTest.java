package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
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
                    .withPhoto(app.properties.getProperty("contact.photo")));
        }
    }


    @Test
    public void testContactEmais() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> ! s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
