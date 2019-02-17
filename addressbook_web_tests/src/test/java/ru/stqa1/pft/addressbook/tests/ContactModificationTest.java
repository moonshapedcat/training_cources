package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));;
    }
  }

  @Test
  public void ContactModificationTest() {
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    ContactData contactData = new ContactData().withId(before.get(index).getId()).withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany");
    app.contact().modify(index, contactData);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contactData);
    Comparator<? super ContactData> byId = (c1, c2)->Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
