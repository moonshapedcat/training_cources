package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanya", "MyTitle", "MyCompany"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1 );
    /*app.getContactHelper().initContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanya", "MyTitle", "MyCompany"));*/
  }
}