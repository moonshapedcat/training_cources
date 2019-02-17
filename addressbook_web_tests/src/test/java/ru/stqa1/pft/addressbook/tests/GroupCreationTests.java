package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData groupData = new GroupData().withName("test2").withHeader("test2").withFooter("test3");
    app.group().create(groupData);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    groupData.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    before.add(groupData);
    Assert.assertEquals(before, after);
  }
}


