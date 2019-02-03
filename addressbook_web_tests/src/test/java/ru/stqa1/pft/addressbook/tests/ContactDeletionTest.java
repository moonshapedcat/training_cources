package ru.stqa1.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa1.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase {
  @Test
  public void ContactDeletionTest() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.wd.switchTo().alert().accept();
    app.wd.findElement(By.cssSelector("div.msgbox"));
  }

}
