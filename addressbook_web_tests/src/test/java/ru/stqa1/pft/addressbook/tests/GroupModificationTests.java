package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.GroupData;
import ru.stqa1.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() {
    Groups before = app.db().groups();
    GroupData modyfiedGroup = before.iterator().next();
    GroupData groupData = new GroupData().withId(modyfiedGroup.getId()).withName("test").withHeader("test2").withFooter("footer");
    app.goTo().groupPage();
    app.group().modify(groupData);
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modyfiedGroup).withAdded(groupData)));
    verifyGroupListOnUI();

  }
}
