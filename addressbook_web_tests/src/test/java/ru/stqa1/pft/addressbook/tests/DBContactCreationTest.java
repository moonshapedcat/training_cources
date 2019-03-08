package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;
import ru.stqa1.pft.addressbook.model.Groups;

public class DBContactCreationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testDBContactCreation() throws Exception {
    Groups groups = app.db().groups();
    ContactData newContact = new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").
                             withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany")
                             .inGroup(groups.iterator().next());
    app.contact().initContact();
    app.contact().fillContactFormDB(newContact, true);
    app.contact().submitContact();
  }
}
