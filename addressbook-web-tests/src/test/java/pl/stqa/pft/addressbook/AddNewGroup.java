package pl.stqa.pft.addressbook;

import org.testng.annotations.*;

public class AddNewGroup extends TestBase {

  @Test
  public void testAddNewGroup() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("testGroup", "headerName", "footerName"));
    submitNewGroup();
    returnToGroupPage();
  }

}
