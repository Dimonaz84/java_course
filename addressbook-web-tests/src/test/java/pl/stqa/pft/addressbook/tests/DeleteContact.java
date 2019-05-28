package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteContact extends TestBase{

    @Test
    public void testDeleteContact() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}
