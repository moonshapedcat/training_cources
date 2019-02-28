package ru.stqa1.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.Contacts;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
   BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
   String xml = "";
   String line = reader.readLine();
   while (line !=null){
     xml +=line;
     line = reader.readLine();
   }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
    return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
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