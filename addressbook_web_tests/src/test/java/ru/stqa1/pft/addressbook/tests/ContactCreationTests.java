package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contactData = new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany");
    app.contact().create(contactData);
    Contacts after = app.contact().all();
    assertThat(after.size(),equalTo(before.size() +1));
    assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}