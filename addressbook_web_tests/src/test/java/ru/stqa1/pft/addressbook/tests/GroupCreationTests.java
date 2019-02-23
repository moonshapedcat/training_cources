package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.GroupData;
import ru.stqa1.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase{


  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData groupData = new GroupData().withName("test2").withHeader("test2").withFooter("test3");
    app.group().create(groupData);
    Groups after = app.group().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    groupData.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt());
    assertThat(after, equalTo(
            before.withAdded(groupData.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
}


