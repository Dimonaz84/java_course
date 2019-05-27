package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

public class AddNewGroup extends TestBase {

  @Test
  public void testAddNewGroup() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("testGroup", "headerName", "footerName"));
    app.getGroupHelper().submitNewGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
