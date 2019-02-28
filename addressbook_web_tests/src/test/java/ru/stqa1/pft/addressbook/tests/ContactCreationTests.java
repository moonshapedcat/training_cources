package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws FileNotFoundException {
    List<Object[]> list =  new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withName("name 1").withMiddlename("middlename1").withLastname("lastname1")
            .withNickname("nickname1").withCompanyName("companyName1").withTitle("title1")});
    list.add(new Object[] {new ContactData().withName("name 2").withMiddlename("middlename2").withLastname("lastname2")
            .withNickname("nickname2").withCompanyName("companyName2").withTitle("title2")});
    list.add(new Object[] {new ContactData().withName("name 3").withMiddlename("middlename3").withLastname("lastname3")
            .withNickname("nickname3").withCompanyName("companyName3").withTitle("title3")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contactData) throws Exception {
    String[] names = new String[] {"test1", "test2", "test3"};
      Contacts before = app.contact().all();
      app.contact().create(contactData);
      Contacts after = app.contact().all();
      assertThat(after.size(),equalTo(before.size() +1));
      assertThat(after, equalTo(before.withAdded(contactData.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}