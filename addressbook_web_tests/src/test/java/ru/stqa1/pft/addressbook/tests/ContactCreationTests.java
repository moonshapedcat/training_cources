package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanya", "MyTitle", "MyCompany"));
  }
}