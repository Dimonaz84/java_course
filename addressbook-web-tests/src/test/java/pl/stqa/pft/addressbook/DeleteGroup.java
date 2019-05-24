package pl.stqa.pft.addressbook;

import org.testng.annotations.*;

public class DeleteGroup extends TestBase{

  @Test
  public void testDeleteGroup() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
