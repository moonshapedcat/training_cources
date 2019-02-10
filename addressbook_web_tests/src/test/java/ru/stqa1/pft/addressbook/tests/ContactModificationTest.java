package ru.stqa1.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase{

  @Test
  public void ContactModificationTest() {

    if (!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanovich", "Ivanov", "Vanya", "MyTitle", "MyCompany"));
      }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Ivanka", "Director", "MyCompany"));
    app.getContactHelper().updateContact();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(new ContactData("Ivan", "Ivanovich", "Ivanov", "Ivanka", "Director", "MyCompany"));
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
