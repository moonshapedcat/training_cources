package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTest extends TestBase{
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));
    }
  }

  @Test
  public void ContactModificationTest() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contactData = new ContactData()
            .withId(modifiedContact.getId()).withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany");
    app.contact().modify(contactData);
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contactData)));
  }

}
