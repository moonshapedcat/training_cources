package ru.stqa1.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;
import ru.stqa1.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create( new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));
    }
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void addContactToGroup(){
  //  app.goTo().homePage();
    ContactData contact = app.db().contacts().iterator().next();
    Groups groups = app.db().groups();
    app.goTo().GroupForAddition(groups.iterator().next().getName());
    if(app.group().isThereContact()){
      app.contact().deleteContactFromGroup(contact);
    }
    GroupData group = groups.iterator().next();
    groups.removeAll(contact.getGroups());
    app.goTo().backToLogo();
    app.contact().addToGroup(contact,group);
    app.db().updateDB(contact);
    assertThat(contact.getGroups(), CoreMatchers.hasItem(group));
  }
}
