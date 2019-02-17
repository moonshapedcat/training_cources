package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size()==0){
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Set<GroupData> before = app.group().all();
    GroupData modyfiedGroup = before.iterator().next();
    GroupData groupData = new GroupData().withId(modyfiedGroup.getId()).withName("test1").withHeader("test2").withFooter("footer");
    app.group().modify(groupData);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modyfiedGroup);
    before.add(groupData);
    Assert.assertEquals(before, after);

  }
}
