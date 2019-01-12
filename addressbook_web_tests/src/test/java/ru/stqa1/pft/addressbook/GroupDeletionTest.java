package ru.stqa1.pft.addressbook;

import org.testng.annotations.Test;


public class GroupDeletionTest extends TestBase{


  @Test
  public void groupDeletionTest1() throws Exception {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returntoGroupPage();
  }

}
