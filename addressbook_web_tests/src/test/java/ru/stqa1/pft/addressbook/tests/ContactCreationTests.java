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
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list =  new ArrayList<Object[]>();
   BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
   String line = reader.readLine();
   while (line !=null){
     String[] split = line.split(";");
     list.add(new Object[] {new ContactData().withName(split[0]).withMiddlename(split[0]).withLastname(split[0])
             .withNickname(split[0]).withCompanyName(split[0]).withTitle(split[0])});
     line = reader.readLine();
   }
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