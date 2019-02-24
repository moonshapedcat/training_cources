package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create( new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));
    }
  }

  @Test
  public void TestContactPhones(){
    for (ContactData contact : app.contact().all()) {
    //ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAllPhones(), equalTo(mergedPhones(contactInfoFromEditForm)));
    }
  }

  private String mergedPhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
            .stream().filter(s -> ! s.equals(""))
            .map(ContactPhoneTest::cleaned)
            .collect(Collectors.joining("\n"));
  }


  public static String cleaned(String phone){
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}

