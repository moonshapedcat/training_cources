package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));
    }
  }

  @Test
  public void TestContactPost(){
    for (ContactData contact : app.contact().all()) {
      //ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
      assertThat(contact.getAllEmails(), equalTo(email(contactInfoFromEditForm)));
    }
  }

  private String email(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter(s -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }
  }

