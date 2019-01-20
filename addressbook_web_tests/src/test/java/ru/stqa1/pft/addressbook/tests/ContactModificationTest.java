package ru.stqa1.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

  @Test
  public void ContactModificationTest() {
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "Ivanka", "Director", "MyCompany"));
    app.getContactHelper().updateContact();
  }
}
