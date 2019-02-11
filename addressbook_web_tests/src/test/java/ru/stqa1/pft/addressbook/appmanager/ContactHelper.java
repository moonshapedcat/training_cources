package ru.stqa1.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa1.pft.addressbook.model.ContactData;
import ru.stqa1.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void fillContactForm(ContactData contactData) {
    //wd.findElement(By.linkText("add new")).click();
    type(By.name("firstname"), contactData.getName());
    type(By.name("middlename"), contactData.getMiddlename());
    //click(By.name("theform"));
    type(By.name("lastname"), contactData.getLastname());
    // click(By.name("theform"));
    type(By.name("nickname"), contactData.getNickname());
    //  click(By.name("theform"));
    type(By.name("title"), contactData.getTitle());
    //  click(By.name("theform"));
    type(By.name("company"), contactData.getCompanyName());
  }

  public void initContact() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }

  public void submitContact() {
    click(By.name("submit"));
  }

  public void editContact() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void createContact(ContactData contactData) {
    initContact();
    fillContactForm(contactData);
    submitContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount(int i) {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for (WebElement element : elements) {
      String id = wd.findElement(By.tagName("input")).getAttribute("value");
      editContact();
      String name = wd.findElement(By.name("firstname")).getAttribute("value");
      String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
      updateContact();

      ContactData contactData = new ContactData(name, null, lastname, null, null, null);
      contacts.add(contactData);
    }

    return contacts;
  }
}

