package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contactData = new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany");
    app.contact().create(contactData);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() +1 );
    /*app.contact().initContact();
    app.contact().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanya", "MyTitle", "MyCompany"));*/

   // contactData.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contactData);
   // Comparator<? super ContactData> byId = (c1, c2)->Integer.compare(c1.getId(), c2.getId());
    Assert.assertEquals(before,after);
  }
}