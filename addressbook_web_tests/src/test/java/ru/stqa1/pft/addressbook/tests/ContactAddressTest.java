package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTest extends TestBase {
  @BeforeMethod
  public void ensurePreconditions(){
    if (app.contact().all().size() == 0){
      app.contact().create(new ContactData().withName("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov")
              .withNickname("Vanya").withTitle("MyTitle").withCompanyName("MyCompany"));
    }
  }

  @Test
  public void TestContactAddress(){
    for (ContactData contact : app.contact().all()) {
      //ContactData contact = app.contact().all().iterator().next();
      ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

      assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
  }
}

