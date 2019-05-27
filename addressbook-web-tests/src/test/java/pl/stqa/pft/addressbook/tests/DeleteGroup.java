package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }

}
