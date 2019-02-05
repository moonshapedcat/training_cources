package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "footer"));
  }
}


