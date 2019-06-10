package pl.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testGroup", "headerName", "footerName"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() -1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
