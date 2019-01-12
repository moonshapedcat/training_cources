package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;


public class GroupDeletionTest extends TestBase{


  @Test
  public void groupDeletionTest1() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returntoGroupPage();
  }

}
