package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup", "headerName", "footerName"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
